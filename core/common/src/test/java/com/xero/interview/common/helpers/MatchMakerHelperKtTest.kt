package com.xero.interview.common.helpers

import com.google.common.truth.Truth.assertThat
import com.xero.interview.data.domain.model.AccountRecord
import com.xero.interview.data.domain.model.TransactionRecord
import kotlinx.coroutines.test.runTest
import org.junit.Test


class MatchMakerHelperKtTest {

    @Test
    fun `matchMake should return correct AccountRecordModel list with matched TransactionRecords`() =
        runTest {
            val result = accountRecordList.matchMake(transactionList)

            assertThat(result.size).isEqualTo(3)
            assertThat(result[0].account).isEqualTo(accountRecordList[0])
            assertThat(result[0].matchedTransactions?.size).isEqualTo(1)
            assertThat(result[0].matchedTransactions?.get(0)).isEqualTo(transactionList[0])

            assertThat(result[1].account).isEqualTo(accountRecordList[1])
            assertThat(result[1].matchedTransactions?.size).isEqualTo(1)
            assertThat(result[1].matchedTransactions?.get(0)).isEqualTo(transactionList[1])

            assertThat(result[2].account).isEqualTo(accountRecordList[2])
            assertThat(result[2].matchedTransactions).isNull()
        }

    @Test
    fun `isAMatch should return correct status after matching record values`() = runTest {
        val matched = transactionList[0].isAMatch(accountRecordList[0])
        assertThat(matched).isTrue()

        val notMatched = transactionList[0].isAMatch(accountRecordList[2])
        assertThat(notMatched).isFalse()
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

    private val accountRecordList = listOf(
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
}