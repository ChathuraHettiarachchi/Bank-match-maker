package com.xero.interview.common.models

import com.xero.interview.data.domain.model.TransactionRecord

data class TransactionRecordModel(
    var record: TransactionRecord,
    var isChecked: Boolean = false,
    var isEnable: Boolean = true,
    var isMatch: Boolean = false,
)
