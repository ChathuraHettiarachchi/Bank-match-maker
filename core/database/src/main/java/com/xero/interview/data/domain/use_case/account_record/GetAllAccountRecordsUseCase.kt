package com.xero.interview.data.domain.use_case.account_record

import com.xero.interview.data.domain.model.AccountRecord
import com.xero.interview.data.domain.repo.AccountRecordRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllAccountRecordsUseCase @Inject constructor(private val repo: AccountRecordRepo) {
    operator fun invoke(bankAccountId: Long): Flow<List<AccountRecord>> = flow {
        repo.allRecords(bankAccountId).collect() {
            emit(it)
        }
    }
}