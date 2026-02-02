const express = require("express");
const app = express();

app.get("/unstable", (req, res) => {
    const p = Math.random();
    if (p < 0.5) {
        res.status(500).json({ ok: false, msg: "simulated error" });
    } else {
        res.json({
            ok: true,
            msg: "service-b: success",
            at: new Date().toISOString(),
        });
    }
});

app.listen(3001, () =>
    console.log("Service B listening on http://localhost:3001"),
);
