const http = require('http');

// TODO 1: In the below line, replace '' with code to import/require transactions data from './data/transactions.json'
const transactions = require('./data/transactions.json');

const server = http.createServer((req, res) => {
  if (req.url === '/') {
    // TODO 2: Implement response for '/' endpoint with proper HTTP status and 'Content-Type' using res.writeHead()
    
    res.writeHead(200, { 'Content-Type': 'text/plain' });
    res.end('Hello PayPal');
  }
  else if (req.url === '/transactions') {
    // TODO 3: Implement response for '/transactions' endpoint with JSON data and proper headers
    
    res.writeHead(200, { 'Content-Type': 'application/json' });
    res.end(JSON.stringify(transactions, null, 2));
  } else {
    res.writeHead(404, { 'Content-Type': 'text/plain' });
    res.end('404 Not Found');
  }
});

const PORT = process.env.PORT || 3000;
server.listen(PORT, () => {
  console.log(`Server is running on http://localhost:${PORT}`);
});
