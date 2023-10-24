package com.xero.interview.common.models

import com.xero.interview.data.domain.model.AccountRecord
import com.xero.interview.data.domain.model.TransactionRecord

data class AccountRecordModel(
    var account: AccountRecord,
    var matchedTransactions: List<TransactionRecord>?
)
