package com.xero.interview.data.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AccountRecord(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var name: String,
    var date: String,
    var amount: Double,
    var isMoneyIn: Boolean = false,
    var bankAccountId: Long
)
