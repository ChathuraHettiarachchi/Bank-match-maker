package com.xero.interview.data.data.repo

import com.xero.interview.data.data.dao.AccountRecordDao
import com.xero.interview.data.domain.model.AccountRecord
import com.xero.interview.data.domain.repo.AccountRecordRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AccountRecordRepoImpl @Inject constructor(private val dao: AccountRecordDao) :
    AccountRecordRepo {
    override fun allRecords(bankAccountId: Long): Flow<List<AccountRecord>> {
        return dao.allRecords(bankAccountId)
    }

    override suspend fun insertRecord(record: AccountRecord): Long {
        return dao.insertRecord(record)
    }

    override suspend fun updateRecord(record: AccountRecord) {
        return dao.updateRecord(record)
    }

}