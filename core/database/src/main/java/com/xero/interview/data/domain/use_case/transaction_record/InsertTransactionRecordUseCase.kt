package com.xero.interview.data.domain.use_case.transaction_record

import com.xero.interview.data.domain.model.TransactionRecord
import com.xero.interview.data.domain.repo.TransactionRecordRepo
import javax.inject.Inject

class InsertTransactionRecordUseCase @Inject constructor(private val repo: TransactionRecordRepo) {
    suspend operator fun invoke(record: TransactionRecord): Long {
        return repo.insertRecord(record)
    }
}