package com.xero.interview.account_records

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
import com.xero.interview.account_records.viewmodel.AccountRecordViewModel
import com.xero.interview.data.domain.model.BankAccount
import com.xero.interview.design.component.actionbar.ActionAppBar
import com.xero.interview.design.component.cell.AccountRecordCell

/**
 * Route triggered by the navigation for AccountRecords screen
 * @param bankAccountId bacnk account id
 * @param onBackClick handle back navigation
 * @param viewModel will inject by di
 * @param navigateToFindMatches will take bank and account ids
 */
@Composable
fun AccountRecordRoute(
    bankAccountId: Long,
    onBackClick: () -> Unit,
    viewModel: AccountRecordViewModel = hiltViewModel(),
    navigateToFindMatches: (Long, Long) -> Unit
) {

    viewModel.loadAccountRecords(bankAccountId)
    val bankAccount by viewModel.bankAccount.collectAsState()

    AccountRecordScreen(
        bankAccount = bankAccount,
        onBackClick = onBackClick,
        navigateToFindMatches = navigateToFindMatches
    )
}

@Composable
fun AccountRecordScreen(
    bankAccount: BankAccount?,
    viewModel: AccountRecordViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    navigateToFindMatches: (Long, Long) -> Unit
) {
    val records by viewModel.matchedRecords.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            ActionAppBar(
                text = "Account records",
                subTitle = bankAccount?.name ?: "",
                onNavigationClick = onBackClick
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    items(records) { record ->
                        AccountRecordCell(
                            record = record.account,
                            matchedRecord = record.matchedTransactions,
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
    AccountRecordRoute(1, {}, hiltViewModel(), { p1, p2 -> })
}