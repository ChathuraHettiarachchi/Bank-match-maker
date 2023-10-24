package com.xero.interview.data.domain.repo

import com.xero.interview.data.domain.model.BankAccount
import kotlinx.coroutines.flow.Flow

interface BankAccountRepo {
    fun allBankAccounts(): Flow<List<BankAccount>>
    suspend fun findBankAccount(id: Long): BankAccount
    suspend fun getSum(): Double
    suspend fun insertAccount(account: BankAccount): Long
    suspend fun updateAccount(account: BankAccount)
}