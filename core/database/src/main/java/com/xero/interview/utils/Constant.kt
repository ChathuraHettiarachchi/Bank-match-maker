package com.xero.interview.utils

import com.xero.interview.data.domain.model.AccountRecord
import com.xero.interview.data.domain.model.BankAccount
import com.xero.interview.data.domain.model.TransactionRecord

object Constant {
    // temp data for database init
    val bankAccounts = listOf<BankAccount>(
        BankAccount(100, 1, "Amana Bank NZ", -10000.00, -20000.00),
        BankAccount(200, 2, "Common Wealth Bank", 6850.00, 6850.00),
        BankAccount(300, 3, "Bank of Ceylon", 92345.12, 92345.12),
    )

    val accountRecords = listOf<AccountRecord>(
        AccountRecord(
            id = 1,
            name = "COC Supermarket",
            date = "20 Oct 2023",
            amount = -682.00,
            bankAccountId = 100
        ),
        AccountRecord(
            id = 2,
            name = "Garage repair",
            date = "20 Oct 2023",
            amount = -1279.00,
            bankAccountId = 100
        ),
        AccountRecord(
            id = 3,
            name = "Garage sale",
            date = "20 Oct 2023",
            amount = 1000.00,
            bankAccountId = 100
        ),
        AccountRecord(
            id = 4,
            name = "Vehicle tuneup",
            date = "20 Oct 2023",
            amount = -1000.00,
            bankAccountId = 100
        ),
        AccountRecord(
            id = 4,
            name = "Television factory",
            date = "20 Oct 2023",
            amount = -1311.00,
            bankAccountId = 100
        ),
        AccountRecord(
            id = 5,
            name = "Garage project",
            date = "20 Oct 2023",
            amount = -1363.00,
            bankAccountId = 100
        ),
        AccountRecord(
            id = 6,
            name = "Saloon Madu POS",
            date = "20 Oct 2023",
            amount = -1345.00,
            bankAccountId = 100
        ),
        AccountRecord(
            id = 7,
            name = "Countdown Mt Roskill",
            date = "20 Oct 2023",
            amount = -1043.00,
            bankAccountId = 100
        ),
        AccountRecord(
            id = 8,
            name = "Pac'n Save LEGIT",
            date = "20 Oct 2023",
            amount = -577.00,
            bankAccountId = 100
        ),
        AccountRecord(
            id = 9,
            name = "Countdown Mt Roskill",
            date = "20 Oct 2023",
            amount = -1053.00,
            bankAccountId = 100
        ),
        AccountRecord(
            id = 10,
            name = "Countdown Mt Roskill",
            date = "20 Oct 2023",
            amount = -1351.00,
            bankAccountId = 100
        ),
        AccountRecord(
            id = 11,
            name = "Countdown Mt Roskill",
            date = "20 Oct 2023",
            amount = -927.00,
            bankAccountId = 100
        ),
    )

    val _accRecordSplits = listOf(
        -382,
        -100,
        -200,
        -1279,
        400,
        600,
        -1000,
        -450,
        -550,
        -311,
        -1363,
        -1000,
        -363,
        -340,
        -1005,
        -700,
        -343,
        -577,
        -153,
        -900,
        -1300,
        -51,
        -327,
        -600
    )

    val transactionRecords = _accRecordSplits.mapIndexed { index, i ->
        TransactionRecord(
            id = (i + 1).toLong(),
            name = "Transaction record ${i + 1}",
            date = "20 Oct 2023",
            type = "Payment",
            amount = i.toDouble(),
            recordId = null,
            bankAccountId = 100
        )
    }
}