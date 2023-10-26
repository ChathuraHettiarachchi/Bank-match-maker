package com.xero.interview.common.data.repo

import com.xero.interview.data.domain.model.TransactionRecord
import com.xero.interview.data.domain.repo.TransactionRecordRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeTransactionRecordRepo : TransactionRecordRepo {
    val transactions = listOf(
        TransactionRecord(
            id = 1,
            name = "Transaction record 1",
            date = "20 Oct 2023",
            type = "Payment",
            amount = 100.00,
            recordId = null,
            bankAccountId = 100
        ), TransactionRecord(
            id = 2,
            name = "Transaction record 2",
            date = "20 Oct 2023",
            type = "Payment",
            amount = 200.00,
            recordId = null,
            bankAccountId = 100
        ),
        TransactionRecord(
            id = 3,
            name = "Transaction record 3",
            date = "20 Oct 2023",
            type = "Payment",
            amount = 300.00,
            recordId = null,
            bankAccountId = 100
        )
    )

    override fun allRecords(bankAccountId: Long): Flow<List<TransactionRecord>> {
        return flow { transactions }
    }

    override suspend fun insertRecord(record: TransactionRecord): Long {
        return 1
    }

    override suspend fun updateRecord(record: TransactionRecord) {
    }
}