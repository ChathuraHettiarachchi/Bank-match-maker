package com.xero.interview.data.domain.use_case.account_record

import com.xero.interview.data.domain.model.AccountRecord
import com.xero.interview.data.domain.repo.AccountRecordRepo
import javax.inject.Inject

class FindAccountRecordUseCase @Inject constructor(private val repo: AccountRecordRepo) {
    operator fun invoke(id: Long): AccountRecord {
        return repo.findAccountRecord(id)
    }
}