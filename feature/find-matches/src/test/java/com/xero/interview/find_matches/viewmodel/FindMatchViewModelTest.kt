package com.xero.interview.find_matches.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.mock
import com.xero.interview.common.helpers.isAMatch
import com.xero.interview.common.models.TransactionRecordModel
import com.xero.interview.data.domain.model.AccountRecord
import com.xero.interview.data.domain.model.TransactionRecord
import com.xero.interview.data.domain.use_case.account_record.FindAccountRecordUseCase
import com.xero.interview.data.domain.use_case.account_record.UpdateAccountRecordUseCase
import com.xero.interview.data.domain.use_case.bank_account.FindBankAccountUseCase
import com.xero.interview.data.domain.use_case.bank_account.UpdateBankAccountUseCase
import com.xero.interview.data.domain.use_case.transaction_record.GetAllTransactionRecordsUseCase
import com.xero.interview.data.domain.use_case.transaction_record.UpdateTransactionRecordUseCase
import com.xero.interview.find_matches.MainDispatcherRule
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.`when`

@RunWith(JUnit4::class)
class FindMatchViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val allTransactionRecordsUseCase: GetAllTransactionRecordsUseCase = mock()
    private val findAccountRecordUseCase: FindAccountRecordUseCase = mock()
    private val updateAccountRecordUseCase: UpdateAccountRecordUseCase = mock()
    private val updateTransactionRecordUseCase: UpdateTransactionRecordUseCase = mock()
    private val updateBankAccountUseCase: UpdateBankAccountUseCase = mock()
    private val findBankAccountUseCase: FindBankAccountUseCase = mock()
    private val viewModel by lazy {
        FindMatchViewModel(
            allTransactionRecordsUseCase,
            findAccountRecordUseCase,
            updateAccountRecordUseCase,
            updateTransactionRecordUseCase,
            updateBankAccountUseCase,
            findBankAccountUseCase
        )
    }


    @Before()
    fun setup() {
        viewModel
        `when`(allTransactionRecordsUseCase(100)).thenReturn(flowOf(transactionList))
        `when`(findAccountRecordUseCase(1)).thenReturn(accountRecord)
    }

    @Test
    fun `find transactions loading with data and set isMatch value to the items matching the account amount`() =
        runTest {
            val result = transactionList.map { i ->
                TransactionRecordModel(
                    record = i,
                    isChecked = false,
                    isEnable = true,
                    isMatch = i.isAMatch(accountRecord)
                )
            }

            viewModel.records.test {
                viewModel.loadTransactions(100, 1)

                val emission = awaitItem()
                assertThat(emission).isEmpty()

                val emission1 = awaitItem()
                assertThat(emission1.size).isEqualTo(result.size)
                assertThat(emission1[0].record.amount).isEqualTo(accountRecord.amount)
                assertThat(emission1[0].isMatch).isTrue()
                cancel()
            }
        }

    @Test
    fun `check amount to match is set on transaction loading`() = runTest {
        val result = transactionList.map { i ->
            TransactionRecordModel(
                record = i,
                isChecked = false,
                isEnable = true,
                isMatch = i.isAMatch(accountRecord)
            )
        }
        viewModel.loadTransactions(100, 1)
        viewModel.amountToMatch.test {
            val emission1 = awaitItem()
            assertThat(emission1).isEqualTo(100.0)
        }
    }


    @Test
    fun `reduce value to match based on user transaction selection`() = runTest {
        val result = transactionList.map { i ->
            TransactionRecordModel(
                record = i,
                isChecked = false,
                isEnable = true,
                isMatch = i.isAMatch(accountRecord)
            )
        }
        viewModel.loadTransactions(100, 1)

        viewModel.amountToMatch.test {
            val emission1 = awaitItem()
            assertThat(emission1).isEqualTo(100.0)

            viewModel.selectTransaction(record = transactionList[0], false)
            val emission2 = awaitItem()
            assertThat(emission2).isEqualTo(0.0)
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

    private val accountRecord = AccountRecord(
        id = 1,
        name = "COC Supermarket",
        date = "20 Oct 2023",
        amount = 100.00,
        bankAccountId = 100
    )
}