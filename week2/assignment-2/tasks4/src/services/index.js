const uuid = require('uuid');

const TXNS = [
    {id: "6166789c-7268-4cfe-8b34-bc8d99cc0581", timestamp: 1626595642375, to: "restaurant", amount: 500},
    {id: "4ad1b27a-28da-45e1-9037-255e8b51b7f6", timestamp: 1626595765426, to: "anjali", amount: 120}
];

const memoryService = {
    async getTransactions(id) {
    },

    async createTransaction({to, amount}) {
    }
}

module.exports = memoryService;