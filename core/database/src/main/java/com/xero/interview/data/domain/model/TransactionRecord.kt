package com.xero.interview.data.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TransactionRecord(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var name: String,
    var date: String,
    var type: String,
    var amount: Double,
    var recordId: Long?,
    var bankAccountId: Long
)
