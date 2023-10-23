package com.xero.interview.data.domain.use_case.bank_account

import com.xero.interview.data.domain.model.BankAccount
import com.xero.interview.data.domain.repo.BankAccountRepo
import javax.inject.Inject

class UpdateBankAccountUseCase @Inject constructor(private val repo: BankAccountRepo) {
    suspend operator fun invoke(record: BankAccount) {
        repo.updateAccount(record)
    }
}