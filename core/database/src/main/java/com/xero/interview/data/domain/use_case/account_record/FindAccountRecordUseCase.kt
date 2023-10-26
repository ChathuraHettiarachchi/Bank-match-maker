package com.xero.interview.data.domain.use_case.account_record

import com.xero.interview.data.domain.model.AccountRecord
import com.xero.interview.data.domain.repo.AccountRecordRepo
import javax.inject.Inject

open class FindAccountRecordUseCase @Inject constructor(private val repo: AccountRecordRepo) {
    open operator fun invoke(id: Long): AccountRecord {
        return repo.findAccountRecord(id)
    }
}