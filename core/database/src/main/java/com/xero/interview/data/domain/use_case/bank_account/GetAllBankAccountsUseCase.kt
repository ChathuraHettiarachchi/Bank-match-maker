package com.xero.interview.data.domain.use_case.bank_account

import com.xero.interview.data.domain.model.BankAccount
import com.xero.interview.data.domain.repo.BankAccountRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllBankAccountsUseCase @Inject constructor(private val repo: BankAccountRepo) {
    operator fun invoke(): Flow<List<BankAccount>> = flow {
        repo.allBankAccounts().collect() {
            emit(it)
        }
    }
}