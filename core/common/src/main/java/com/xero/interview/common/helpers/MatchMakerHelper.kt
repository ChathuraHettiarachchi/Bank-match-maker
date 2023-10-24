package com.xero.interview.common.helpers

import com.xero.interview.common.models.AccountRecordModel
import com.xero.interview.data.domain.model.AccountRecord
import com.xero.interview.data.domain.model.TransactionRecord

fun List<AccountRecord>.matchMake(transactionRecords: List<TransactionRecord>): List<AccountRecordModel> {
    val transactionMap = transactionRecords.groupBy { it.amount }
    val results = mutableListOf<AccountRecordModel>()

    this.forEach { account ->
        val matchingTransactions = transactionMap[account.amount]

        val accountRecordModel = if (matchingTransactions != null) {
            AccountRecordModel(account = account, matchedTransactions = matchingTransactions)
        } else {
            AccountRecordModel(account = account, matchedTransactions = null)
        }

        results.add(accountRecordModel)
    }

    return results
}
