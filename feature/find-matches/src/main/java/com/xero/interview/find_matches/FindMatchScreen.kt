package com.xero.interview.find_matches

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.xero.interview.data.domain.model.TransactionRecord
import com.xero.interview.design.component.actionbar.ActionAppBar
import com.xero.interview.design.component.cell.TransactionCell
import com.xero.interview.design.component.indicator.MatchesIndicator
import com.xero.interview.find_matches.viewmodel.FindMatchViewModel

/**
 * Use to display the find a match screen from routes
 * @param bankAccountId use to get the back account id
 * @param onBackClick handle back navigation
 * @param accountId use to get account id
 * @param viewModel will inject by di
 */
@Composable
fun FindMatchRoute(
    bankAccountId: Long, accountId: Long, onBackClick: () -> Unit,
    viewModel: FindMatchViewModel = hiltViewModel()
) {
    viewModel.loadTransactions(bankAccountId = bankAccountId, accountId = accountId)
    FindMatchScreen(onBackClick = onBackClick)
}

/**
 * UI for find a match screen
 * @param viewModel will inject by di
 * @param onBackClick to handle back click
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FindMatchScreen(
    viewModel: FindMatchViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
) {
    val account by viewModel.account.collectAsState()
    val data by viewModel.records.collectAsState()
    val amountToMatch by viewModel.amountToMatch.collectAsState()
    val errorData by viewModel.errorData.collectAsState()

    fun onCellClick(record: TransactionRecord, isChecked: Boolean) {
        viewModel.selectTransaction(record, isChecked)
    }

    fun onBackClickTrigger() {
        viewModel.updateDbRecords()
        onBackClick.invoke()
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            ActionAppBar(
                text = "Find matches",
                subTitle = account?.name,
                onNavigationClick = ::onBackClickTrigger
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    stickyHeader {
                        MatchesIndicator(amountToMatch ?: 0.0)
                    }
                    items(data) { i ->
                        TransactionCell(
                            record = i.record,
                            isMatched = i.isMatch,
                            isChecked = i.isChecked,
                            isEnabled = i.isEnable,
                            onClick = ::onCellClick
                        )
                    }
                }
            }
            if (errorData.isError) {

            }
        }
    }
}

@Preview
@Composable
fun PreviewFindMatchScreen() {
    FindMatchRoute(1, 1, {})
}