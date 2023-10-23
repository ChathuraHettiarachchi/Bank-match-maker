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
    @Query("SELECT * FROM AccountRecord WHERE bankAccountId = :bankAccountId")
    suspend fun allRecords(bankAccountId: Long): Flow<List<AccountRecord>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecord(record: AccountRecord): Long

    @Update
    suspend fun updateRecord(record: AccountRecord)
}