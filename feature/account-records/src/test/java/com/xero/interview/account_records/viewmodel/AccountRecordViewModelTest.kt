package com.xero.interview.account_records.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.mock
import com.xero.interview.account_records.MainDispatcherRule
import com.xero.interview.common.models.AccountRecordModel
import com.xero.interview.data.domain.model.AccountRecord
import com.xero.interview.data.domain.model.BankAccount
import com.xero.interview.data.domain.model.TransactionRecord
import com.xero.interview.data.domain.use_case.account_record.GetAllAccountRecordsUseCase
import com.xero.interview.data.domain.use_case.bank_account.FindBankAccountUseCase
import com.xero.interview.data.domain.use_case.transaction_record.GetAllTransactionRecordsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.`when`

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(JUnit4::class)
class AccountRecordViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val allAccountRecordsUseCase: GetAllAccountRecordsUseCase = mock()
    private val findBankAccountUseCase: FindBankAccountUseCase = mock()
    private val allTransactionRecordsUseCase: GetAllTransactionRecordsUseCase = mock()
    private val viewModel by lazy {
        AccountRecordViewModel(
            allAccountRecordsUseCase,
            findBankAccountUseCase,
            allTransactionRecordsUseCase
        )
    }

    @Before()
    fun setup() = runTest {
        viewModel
        `when`(allTransactionRecordsUseCase(100)).thenReturn(flowOf(transactionList))
        `when`(allAccountRecordsUseCase(100)).thenReturn(flowOf(accountRecord))
        `when`(findBankAccountUseCase(100)).thenReturn(bankAccount)
    }

    @Test
    fun `load account records based on bankAccountId`() = runTest {
        viewModel.matchedRecords.test {
            viewModel.loadAccountRecords(100)
            val emission1 = awaitItem()
            val emission2 = awaitItem()

            assertThat(emission1).isEmpty()
            assertThat(emission2).isNotEmpty()
        }
    }

    @Test
    fun `add matching transaction records to the List-AccountRecordModel`() = runTest {
        viewModel.matchedRecords.test {
            viewModel.loadAccountRecords(100)
            val emission1 = awaitItem()
            val emission2 = awaitItem()

            assertThat(emission1).isEmpty()
            assertThat(emission2).isNotEmpty()
            assertThat(emission2.size).isEqualTo(matchedTransactionsWithAccount.size)
            assertThat(emission2[0].matchedTransactions).isEqualTo(listOf(transactionList[0]))
        }
    }

    @Test
    fun `set null if there are no matching transaction records`() = runTest {
        viewModel.matchedRecords.test {
            viewModel.loadAccountRecords(100)
            val emission1 = awaitItem()
            val emission2 = awaitItem()

            assertThat(emission1).isEmpty()
            assertThat(emission2).isNotEmpty()
            assertThat(emission2.size).isEqualTo(matchedTransactionsWithAccount.size)
            assertThat(emission2[2].matchedTransactions).isEqualTo(null)
        }
    }

    private val transactionList = listOf(
        TransactionRecord(
            id = 1,
            name = "Transaction record 1",
            date = "20 Oct 2023",
            type = "Payment",
            amount = 100.00,
            recordId = null,
            bankAccountId = 100
        ),
        TransactionRecord(
            id = 2,
            name = "Transaction record 1",
            date = "20 Oct 2023",
            type = "Payment",
            amount = 200.00,
            recordId = null,
            bankAccountId = 200
        )
    )

    private val accountRecord = listOf(
        AccountRecord(
            id = 1,
            name = "COC Supermarket",
            date = "20 Oct 2023",
            amount = 100.00,
            bankAccountId = 100
        ),
        AccountRecord(
            id = 2,
            name = "COC Supermarket",
            date = "20 Oct 2023",
            amount = 200.00,
            bankAccountId = 100
        ),
        AccountRecord(
            id = 3,
            name = "COC Supermarket",
            date = "20 Oct 2023",
            amount = 400.00,
            bankAccountId = 100
        )
    )

    private val bankAccount = BankAccount(
        id = 100,
        icon = 1,
        name = "Amana Bank NZ",
        appBalance = -10000.00,
        statementBalance = -20000.00,
        updatedAt = "20 Oct 2023"
    )

    private val matchedTransactionsWithAccount = listOf(
        AccountRecordModel(
            account = accountRecord[0],
            matchedTransactions = listOf(transactionList[0])
        ),
        AccountRecordModel(
            account = accountRecord[1],
            matchedTransactions = listOf(transactionList[1])
        ),
        AccountRecordModel(
            account = accountRecord[2],
            matchedTransactions = null
        )
    )
}