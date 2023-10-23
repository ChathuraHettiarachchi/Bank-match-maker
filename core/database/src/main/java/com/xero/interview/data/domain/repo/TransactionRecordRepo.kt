package com.xero.interview.data.domain.repo

import com.xero.interview.data.domain.model.TransactionRecord
import kotlinx.coroutines.flow.Flow

interface TransactionRecordRepo {
    suspend fun allRecords(bankAccountId: Long): Flow<List<TransactionRecord>>
    suspend fun insertRecord(record: TransactionRecord): Long
    suspend fun updateRecord(record: TransactionRecord)
}