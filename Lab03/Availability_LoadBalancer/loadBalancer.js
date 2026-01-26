const http = require('http');
const httpProxy = require('http-proxy');

const proxy = httpProxy.createProxyServer();

const servers = [
  { target: 'http://localhost:3000', name: 'Server 1' },
  { target: 'http://localhost:3001', name: 'Server 2' },
  { target: 'http://localhost:3002', name: 'Server 3' }
];

let currentIndex = 0; 

http.createServer((req, res) => {
  const targetServer = servers[currentIndex];

  console.log(`Request received, routing to ${targetServer.name}`);

  proxy.web(req, res, { target: targetServer.target });

  currentIndex = (currentIndex + 1) % servers.length;
}).listen(8080, () => {
  console.log('Load Balancer is running on port 8080');
});
