package com.xero.interview.data.domain.use_case.bank_account

import com.xero.interview.data.domain.model.BankAccount
import com.xero.interview.data.domain.repo.BankAccountRepo
import javax.inject.Inject

class InsertBankAccountUseCase @Inject constructor(private val repo: BankAccountRepo) {
    suspend operator fun invoke(record: BankAccount): Long {
        return repo.insertAccount(record)
    }
}