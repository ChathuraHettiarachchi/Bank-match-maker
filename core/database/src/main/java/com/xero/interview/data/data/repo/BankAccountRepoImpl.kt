package com.xero.interview.data.data.repo

import com.xero.interview.data.data.dao.BankAccountDao
import com.xero.interview.data.domain.model.BankAccount
import com.xero.interview.data.domain.repo.BankAccountRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BankAccountRepoImpl @Inject constructor(private val dao: BankAccountDao) : BankAccountRepo {
    override fun allBankAccounts(): Flow<List<BankAccount>> {
        return dao.allBankAccounts()
    }

    override suspend fun insertAccount(account: BankAccount): Long {
        return dao.insertAccount(account)
    }

    override suspend fun updateAccount(account: BankAccount) {
        return dao.updateAccount(account)
    }

}