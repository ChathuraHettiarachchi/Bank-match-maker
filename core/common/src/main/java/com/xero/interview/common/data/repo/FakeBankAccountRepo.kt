package com.xero.interview.common.data.repo

import com.xero.interview.data.domain.model.BankAccount
import com.xero.interview.data.domain.repo.BankAccountRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeBankAccountRepo : BankAccountRepo {
    val bankAccounts = listOf<BankAccount>(
        BankAccount(100, 1, "Amana Bank NZ", -10000.00, -20000.00, "20 Oct 2023"),
        BankAccount(200, 2, "Common Wealth Bank", 6850.00, 6850.00, "20 Oct 2023"),
        BankAccount(300, 3, "Bank of Ceylon", 92345.12, 92345.12, "20 Oct 2023"),
    )

    override fun allBankAccounts(): Flow<List<BankAccount>> {
        return flow { bankAccounts }
    }

    override suspend fun findBankAccount(id: Long): BankAccount {
        return bankAccounts[0]
    }

    override suspend fun getSum(): Double {
        return 330.00
    }

    override suspend fun insertAccount(account: BankAccount): Long {
        return 1
    }

    override suspend fun updateAccount(account: BankAccount) {
    }
}