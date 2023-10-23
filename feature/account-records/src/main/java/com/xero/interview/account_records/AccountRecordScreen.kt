package com.xero.interview.account_records

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.xero.interview.account_records.viewmodel.AccountRecordViewModel
import com.xero.interview.data.domain.model.AccountRecord
import com.xero.interview.data.domain.model.BankAccount
import com.xero.interview.design.component.actionbar.ActionAppBar
import com.xero.interview.design.component.cell.AccountRecordCell

@Composable
fun AccountRecordRoute(
    bankAccountId: Long,
    onBackClick: () -> Unit,
    navigateToFindMatches: (Long, Long) -> Unit
) {
    val bankAccount = BankAccount((0..999).random().toLong(), 1, "Amana Bank NZ", 92345.12, 23425.00)
    val data: MutableList<AccountRecord> = mutableListOf();

    repeat((0..10).count()) {
        data.add(AccountRecord((0..999).random().toLong(), "Test name", "12 Dec 2023", 50012.23, false, bankAccountId))
    }

    AccountRecordScreen(
        bankAccount = bankAccount,
        data = data,
        onBackClick = onBackClick,
        navigateToFindMatches = navigateToFindMatches
    )

    Log.e("--->", bankAccountId.toString())
}

@Composable
fun AccountRecordScreen(
    bankAccount: BankAccount,
    data: List<AccountRecord>,
    viewModel: AccountRecordViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    navigateToFindMatches: (Long, Long) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            ActionAppBar(
                text = "Account records",
                subTitle = bankAccount.name,
                onNavigationClick = onBackClick
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    items(data) { record ->
                        AccountRecordCell(
                            record = record,
                            onClick = navigateToFindMatches
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewAccountRecordScreen() {
    AccountRecordRoute(1, {}, { p1, p2 -> })
}