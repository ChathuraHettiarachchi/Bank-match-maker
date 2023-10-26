package com.xero.interview.data.domain.use_case.transaction_record

import com.xero.interview.data.domain.model.TransactionRecord
import com.xero.interview.data.domain.repo.TransactionRecordRepo
import javax.inject.Inject

open class UpdateTransactionRecordUseCase @Inject constructor(private val repo: TransactionRecordRepo) {
    open suspend operator fun invoke(record: TransactionRecord) {
        repo.updateRecord(record)
    }
}