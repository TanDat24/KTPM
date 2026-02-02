const axios = require("axios");

async function main() {
    const url = process.env.URL || "http://localhost:3000/call";
    const concurrency = Number.parseInt(process.env.N || "10", 10);
    const delayMs = Number.parseInt(process.env.DELAY_MS || "2000", 10);

    const requests = Array.from({ length: concurrency }, (_, i) => {
        const clientIp = `10.0.0.${i + 1}`;
        return axios.get(url, {
            params: { delayMs },
            timeout: 10_000,
            validateStatus: () => true,
            headers: {
                // Ensure each request has a unique key so rate limiting doesn't mask bulkhead behavior.
                "x-forwarded-for": clientIp,
            },
        });
    });

    const results = await Promise.all(requests);

    const counts = new Map();
    for (const r of results) {
        counts.set(r.status, (counts.get(r.status) || 0) + 1);
    }

    console.log(
        `Sent ${concurrency} parallel requests to ${url}?delayMs=${delayMs}`,
    );
    console.log("Status counts:");
    for (const [status, count] of Array.from(counts.entries()).sort(
        (a, b) => a[0] - b[0],
    )) {
        console.log(`  ${status}: ${count}`);
    }

    console.log("\nSample responses:");
    for (const r of results.slice(0, Math.min(5, results.length))) {
        const reason =
            r.data && typeof r.data === "object" ? r.data.reason : undefined;
        console.log(`  ${r.status}${reason ? ` (${reason})` : ""}`);
    }
}

main().catch((err) => {
    console.error(err?.stack || String(err));
    process.exitCode = 1;
});
