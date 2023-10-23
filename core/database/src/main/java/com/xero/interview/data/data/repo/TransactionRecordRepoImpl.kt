package com.xero.interview.data.data.repo

import com.xero.interview.data.data.dao.TransactionRecordDao
import com.xero.interview.data.domain.model.TransactionRecord
import com.xero.interview.data.domain.repo.TransactionRecordRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TransactionRecordRepoImpl @Inject constructor(private val dao: TransactionRecordDao) :
    TransactionRecordRepo {
    override fun allRecords(bankAccountId: Long): Flow<List<TransactionRecord>> {
        return dao.allRecords(bankAccountId)
    }

    override suspend fun insertRecord(record: TransactionRecord): Long {
        return dao.insertRecord(record)
    }

    override suspend fun updateRecord(record: TransactionRecord) {
        return dao.updateRecord(record)
    }
}