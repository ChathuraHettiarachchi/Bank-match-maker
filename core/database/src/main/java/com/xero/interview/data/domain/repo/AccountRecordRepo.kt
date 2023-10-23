package com.xero.interview.data.domain.repo

import com.xero.interview.data.domain.model.AccountRecord
import kotlinx.coroutines.flow.Flow

interface AccountRecordRepo {
    fun allRecords(bankAccountId: Long): Flow<List<AccountRecord>>
    suspend fun insertRecord(record: AccountRecord): Long
    suspend fun updateRecord(record: AccountRecord)

}