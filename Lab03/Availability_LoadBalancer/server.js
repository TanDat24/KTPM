const express = require('express');
const app = express();

const serverId = process.argv[2] || 'Server 1';
const port = process.argv[3] || 3000; 

app.get('/', (req, res) => {
  res.send(`Hello from ${serverId}`);
});

app.listen(port, () => {
  console.log(`${serverId} is running on port ${port}`);
});
