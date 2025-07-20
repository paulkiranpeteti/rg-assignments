const txnService = require('../services');

async function getTransactions(req, res) {
    const val = await txnService.getTransactions();
    return res.json(val);
}

async function getSingleTransaction(req, res) {
    const { id } = req.params;
    const txn = await txnService.getTransactions(id);
    if (txn) {
        return res.json(txn);
    } else {
        return res.status(404).json({ error: 'Transaction not found' });
    }
}

async function createTransaction(req, res) {
    const details = req.body;
    const newId = await txnService.createTransaction(details);
    return res.status(201).json(newId);
}

module.exports = { getTransactions, getSingleTransaction,createTransaction };