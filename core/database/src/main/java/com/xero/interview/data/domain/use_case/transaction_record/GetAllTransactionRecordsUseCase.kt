package com.xero.interview.data.domain.use_case.transaction_record

import com.xero.interview.data.domain.model.TransactionRecord
import com.xero.interview.data.domain.repo.TransactionRecordRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

open class GetAllTransactionRecordsUseCase @Inject constructor(private val repo: TransactionRecordRepo) {
    open operator fun invoke(bankAccountId: Long): Flow<List<TransactionRecord>> = flow {
        repo.allRecords(bankAccountId).collect() {
            emit(it)
        }
    }
}