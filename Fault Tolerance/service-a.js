// service-a.js
const express = require("express");
const axios = require("axios");
const {
    Policy,
    ConsecutiveBreaker,
    ExponentialBackoff,
    retry,
    circuitBreaker,
    bulkhead,
    handleAll,
    wrap,
} = require("cockatiel");
const { RateLimiterMemory } = require("rate-limiter-flexible");

const app = express();

// Rate limit per client so it is observable in tests.
// 5 requests / 10 seconds per IP.
const rateLimiter = new RateLimiterMemory({ points: 5, duration: 10 });

const myBulkhead = bulkhead(3, 2);

// retry policy: tối đa 3 attempts với exponential backoff
const retryPolicy = retry(handleAll, {
    maxAttempts: 3,
    backoff: new ExponentialBackoff({ initial: 100, maxDelay: 2000 }),
});

// circuit breaker: mở khi có 5 lỗi liên tiếp, thử half-open sau 10s
const circuit = circuitBreaker(handleAll, {
    halfOpenAfter: 10_000,
    breaker: new ConsecutiveBreaker(5),
});

// kết hợp: retry trước, sau đó circuit
const combined = wrap(retryPolicy, circuit);

function getClientKey(req) {
    const xff = req.headers["x-forwarded-for"];
    const forwarded = Array.isArray(xff) ? xff[0] : xff;
    const ipFromHeader = typeof forwarded === "string" ? forwarded.split(",")[0].trim() : "";
    return ipFromHeader || req.ip || "unknown";
}

async function callServiceB(rateLimitKey) {
    try {
        await rateLimiter.consume(rateLimitKey);
    } catch (rlRejected) {
        throw new Error("RateLimitExceeded");
    }

    // 2) bulkhead.execute để giới hạn concurrency
    return myBulkhead.execute(async () => {
        // Optional delay to help demonstrate bulkhead saturation in tests.
        // Example: /call?delayMs=2000
        if (typeof callServiceB.delayMs === "number" && callServiceB.delayMs > 0) {
            await new Promise((resolve) => setTimeout(resolve, callServiceB.delayMs));
        }
        return combined.execute(async () => {
            const resp = await axios.get("http://localhost:3001/unstable", {
                timeout: 3000,
            });
            if (resp.status >= 400) throw new Error("UpstreamError");
            return resp.data;
        });
    });
}

app.get("/call", async (req, res) => {
    try {
        const rateLimitKey = getClientKey(req);
        const delayMsRaw = req.query.delayMs;
        const delayMs = Number.parseInt(String(delayMsRaw ?? "0"), 10);
        callServiceB.delayMs = Number.isFinite(delayMs) ? Math.max(0, Math.min(delayMs, 30_000)) : 0;

        const data = await callServiceB(rateLimitKey);
        res.json({ ok: true, data });
    } catch (e) {
        if (e.message === "RateLimitExceeded") {
            return res.status(429).json({ ok: false, reason: "rate_limited" });
        }
        // bulkhead rejection
        if (e.name === "BulkheadRejectedError") {
            return res
                .status(503)
                .json({ ok: false, reason: "bulkhead_rejected" });
        }
        // circuit open or retries failed
        if (e.name && e.name.includes("Breaker")) {
            return res.status(503).json({
                ok: false,
                reason: "circuit_open_or_fail",
                detail: e.message,
            });
        }
        res.status(502).json({
            ok: false,
            reason: "upstream_failed",
            message: e.message,
        });
    }
});

app.listen(3000, () =>
    console.log("Service A listening on http://localhost:3000"),
);
