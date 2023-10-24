package com.xero.interview.data.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BankAccount(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var icon: Int,
    var name: String,
    var appBalance: Double,
    var statementBalance: Double,
    var updatedAt: String,
)
