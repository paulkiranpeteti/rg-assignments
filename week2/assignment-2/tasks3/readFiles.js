const fs = require("fs").promises;
const path = require("path");

async function displayTransactions() {
  try {
    const filePath = path.join(__dirname, "./data/transactions.json");
    const data = await fs.readFile(filePath, "utf-8");
    const transactions = JSON.parse(data);
    console.log(transactions);
  } 
  catch (error) {
    console.log("Failed to Load the Transactions")
  }
}

displayTransactions();
