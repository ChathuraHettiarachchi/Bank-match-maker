package com.xero.interview.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.xero.interview.data.domain.model.BankAccount
import com.xero.interview.design.component.actionbar.MainAppBar
import com.xero.interview.design.component.cell.AccountSummary
import com.xero.interview.design.component.cell.BankAccountCell
import com.xero.interview.design.component.text.TitleText
import com.xero.interview.design.component.utils.HSpace
import com.xero.interview.design.theme.blackColor
import com.xero.interview.design.theme.defaultMargin
import com.xero.interview.design.theme.halfMargin
import com.xero.interview.design.theme.titleText
import com.xero.interview.home.viewmodel.HomeViewModel

@Composable
fun HomeRoute(
    navigateToAccountRecords: (Long) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    HomeScreen(navigateToAccountRecords, viewModel)
}

@Composable
fun HomeScreen(
    navigateToAccountRecords: (Long) -> Unit,
    viewModel: HomeViewModel
) {
    val scrollState = rememberScrollState()
    val totalAmount by viewModel.totalAmount.collectAsState()
    val bankAccounts by viewModel.bankAccounts.collectAsState()

    fun navigateToRecords(id: Long) {
        navigateToAccountRecords(id)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            MainAppBar("Xero Accounting")
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(defaultMargin)
            ) {
                AccountSummary(
                    title = "All accounts",
                    subTitle = "-- last updated on 12 Dec 2023 --",
                    amount = totalAmount
                )
                HSpace(defaultMargin)
                Card() {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        HSpace(halfMargin)
                        TitleText(
                            text = "Bank accounts",
                            style = titleText.copy(color = blackColor)
                        )
                        HSpace(halfMargin)
                        LazyColumn(
                            modifier = Modifier
                                .weight(1f),
                            verticalArrangement = Arrangement.spacedBy(
                                1.dp
                            )
                        ) {
                            items(bankAccounts) { account ->
                                BankAccountCell(
                                    account = account,
                                    infoText = "This is a testing",
                                    onClick = { navigateToRecords(it) }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

val tempAccounts = listOf<BankAccount>(
    BankAccount(12, 1, "Amana Bank NZ", 92345.12, 23425.00),
    BankAccount(22, 1, "Amana Bank NZ", 92345.12, 92345.12),
    BankAccount(424, 1, "Amana Bank NZ", 92345.12, 23425.00)
)

@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen(navigateToAccountRecords = {}, viewModel = hiltViewModel())
}