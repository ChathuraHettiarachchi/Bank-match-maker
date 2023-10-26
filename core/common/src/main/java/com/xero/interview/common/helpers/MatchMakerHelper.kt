package com.xero.interview.common.helpers

import com.xero.interview.common.models.AccountRecordModel
import com.xero.interview.data.domain.model.AccountRecord
import com.xero.interview.data.domain.model.TransactionRecord

/**
 * matchMake is an extension function for List<AccountRecord> to match List<AccountRecord> with List<TransactionRecord>
 * in logic it will create separate list to combine account record with matched list of transactions
 * @param transactionRecords is a list of List<TransactionRecord>
 */
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

fun TransactionRecord.isAMatch(accountRecord: AccountRecord): Boolean {
    // for now I'm considering the amount
    return this.amount == accountRecord.amount
}
