package com.xero.interview.data.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.xero.interview.data.domain.model.TransactionRecord
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionRecordDao {
    @Query("SELECT * FROM TransactionRecord WHERE bankAccountId = :bankAccountId AND recordId IS NULL")
    fun allRecords(bankAccountId: Long): Flow<List<TransactionRecord>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecord(record: TransactionRecord): Long

    @Update
    suspend fun updateRecord(record: TransactionRecord)
}