package com.xero.interview.find_matches

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
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
import com.xero.interview.data.domain.model.AccountRecord
import com.xero.interview.data.domain.model.TransactionRecord
import com.xero.interview.design.component.actionbar.ActionAppBar
import com.xero.interview.design.component.cell.TransactionCell
import com.xero.interview.design.component.indicator.MatchesIndicator
import com.xero.interview.find_matches.viewmodel.FindMatchViewModel

@Composable
fun FindMatchRoute(
    bankAccountId: Long, accountId: Long, onBackClick: () -> Unit
) {
    val acc = AccountRecord(accountId, "Test name", "12 Dec 2023", 50012.23, bankAccountId)
    val data: MutableList<TransactionRecord> = mutableListOf();
    repeat((0..10).count()) {
        data.add(
            TransactionRecord(
                (0..999).random().toLong(),
                "Test name",
                "12 Dec 2023",
                "Payment",
                1234.12,
                accountId,
                bankAccountId
            )
        )
    }

    FindMatchScreen(bankAccountId, acc, data, onBackClick = onBackClick)
    Log.e("--->", bankAccountId.toString())
    Log.e("--->", accountId.toString())
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FindMatchScreen(
    bankAccountId: Long,
    account: AccountRecord,
    data: List<TransactionRecord>,
    viewModel: FindMatchViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            ActionAppBar(
                text = "Find matches",
                subTitle = account.name,
                onNavigationClick = onBackClick
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    stickyHeader {
                        MatchesIndicator(account.amount)
                    }
                    items(data) { record ->
                        TransactionCell(record = record)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewFindMatchScreen() {
    FindMatchRoute(1, 1, {})
}