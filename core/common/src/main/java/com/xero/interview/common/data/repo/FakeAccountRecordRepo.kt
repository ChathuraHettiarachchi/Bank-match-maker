package com.xero.interview.common.data.repo

import com.xero.interview.data.domain.model.AccountRecord
import com.xero.interview.data.domain.repo.AccountRecordRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeAccountRecordRepo : AccountRecordRepo {
    val accountRecords = listOf<AccountRecord>(
        AccountRecord(
            id = 1,
            name = "COC Supermarket",
            date = "20 Oct 2023",
            amount = -682.00,
            bankAccountId = 100
        ),
        AccountRecord(
            id = 2,
            name = "Garage repair",
            date = "20 Oct 2023",
            amount = -1279.00,
            bankAccountId = 100
        ),
        AccountRecord(
            id = 3,
            name = "Garage sale",
            date = "20 Oct 2023",
            amount = 1000.00,
            bankAccountId = 100
        )
    )

    override fun allRecords(bankAccountId: Long): Flow<List<AccountRecord>> {
        return flow { accountRecords }
    }

    override fun findAccountRecord(id: Long): AccountRecord {
        return accountRecords[0]
    }

    override suspend fun countRecords(bankAccountId: Long): Int {
        return 10
    }

    override suspend fun insertRecord(record: AccountRecord): Long {
        return 1
    }

    override suspend fun updateRecord(record: AccountRecord) {
    }
}