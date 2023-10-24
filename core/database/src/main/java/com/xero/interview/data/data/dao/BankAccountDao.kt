package com.xero.interview.data.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.xero.interview.data.domain.model.BankAccount
import kotlinx.coroutines.flow.Flow

@Dao
interface BankAccountDao {
    @Query("SELECT * FROM BankAccount")
    fun allBankAccounts(): Flow<List<BankAccount>>

    @Query("SELECT * FROM BankAccount WHERE id = :id LIMIT 1")
    suspend fun findBankAccount(id: Long): BankAccount

    @Query("SELECT SUM(appBalance) FROM BankAccount")
    suspend fun getSum(): Double

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAccount(account: BankAccount): Long

    @Update
    suspend fun updateAccount(account: BankAccount)
}