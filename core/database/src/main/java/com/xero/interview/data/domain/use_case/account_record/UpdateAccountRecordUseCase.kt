package com.xero.interview.data.domain.use_case.account_record

import com.xero.interview.data.domain.model.AccountRecord
import com.xero.interview.data.domain.repo.AccountRecordRepo
import javax.inject.Inject

class UpdateAccountRecordUseCase @Inject constructor(private val repo: AccountRecordRepo) {
    suspend operator fun invoke(record: AccountRecord) {
        repo.updateRecord(record)
    }
}