package com.xero.interview.data.domain.use_case.account_record

import com.xero.interview.data.domain.repo.AccountRecordRepo
import javax.inject.Inject

open class CountAccountRecordUseCase @Inject constructor(private val repo: AccountRecordRepo) {
    open suspend operator fun invoke(bankAccountId: Long): Int {
        return repo.countRecords(bankAccountId)
    }
}