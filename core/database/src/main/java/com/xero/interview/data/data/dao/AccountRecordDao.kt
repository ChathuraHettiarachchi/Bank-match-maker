package com.xero.interview.data.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.xero.interview.data.domain.model.AccountRecord
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountRecordDao {
    @Query("SELECT * FROM AccountRecord WHERE bankAccountId = :bankAccountId AND isMatched = 0")
    fun allRecords(bankAccountId: Long): Flow<List<AccountRecord>>

    @Query("SELECT COUNT(id) FROM AccountRecord WHERE bankAccountId = :bankAccountId AND isMatched = 0")
    suspend fun countRecords(bankAccountId: Long): Int

    @Query("SELECT * FROM AccountRecord WHERE id = :id LIMIT 1")
    fun findAccountRecord(id: Long): AccountRecord

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecord(record: AccountRecord): Long

    @Update
    suspend fun updateRecord(record: AccountRecord)
}