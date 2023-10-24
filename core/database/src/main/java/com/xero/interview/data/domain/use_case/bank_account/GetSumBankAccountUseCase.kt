package com.xero.interview.data.domain.use_case.bank_account

import com.xero.interview.data.domain.repo.BankAccountRepo
import javax.inject.Inject

class GetSumBankAccountUseCase @Inject constructor(private val repo: BankAccountRepo) {
    suspend operator fun invoke(): Double {
        return repo.getSum() ?: 0.0
    }
}