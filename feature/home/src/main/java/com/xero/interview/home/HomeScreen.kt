package com.xero.interview.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.xero.interview.design.component.actionbar.MainAppBar
import com.xero.interview.design.component.cell.AccountSummary
import com.xero.interview.design.component.cell.BankAccountCell
import com.xero.interview.design.component.text.TitleText
import com.xero.interview.design.component.utils.HSpace
import com.xero.interview.design.theme.blackColor
import com.xero.interview.design.theme.defaultMargin
import com.xero.interview.design.theme.defaultRadius
import com.xero.interview.design.theme.halfMargin
import com.xero.interview.design.theme.separatorColor
import com.xero.interview.design.theme.titleText
import com.xero.interview.design.theme.whiteColor
import com.xero.interview.home.viewmodel.HomeViewModel

@Composable
fun HomeRoute(
    navigateToAccountRecords: (Long) -> Unit
) {
    HomeScreen(navigateToAccountRecords)
}

@Composable
fun HomeScreen(
    navigateToAccountRecords: (Long) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    val totalAmount by viewModel.totalAmount.collectAsState()
    val bankAccounts by viewModel.bankAccounts.collectAsState()

    fun navigateToRecords(id: Long) {
        navigateToAccountRecords(id)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = whiteColor
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            MainAppBar("Xero Accounting")
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(defaultMargin)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(
                        1.dp
                    )
                ) {
                    item {
                        AccountSummary(
                            title = "All accounts",
                            subTitle = "-- last updated on 12 Dec 2023 --",
                            amount = totalAmount
                        )
                        HSpace(defaultMargin)
                    }
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    shape = RoundedCornerShape(
                                        topStart = defaultRadius,
                                        topEnd = defaultRadius
                                    ), color = separatorColor
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            HSpace(halfMargin)
                            TitleText(
                                text = "Bank accounts",
                                style = titleText.copy(color = blackColor)
                            )
                            HSpace(halfMargin)
                        }
                    }
                    items(bankAccounts) { data ->
                        BankAccountCell(
                            account = data.bankAccount,
                            infoText = data.accountRecordCount,
                            onClick = { navigateToRecords(it) }
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen(navigateToAccountRecords = {}, viewModel = hiltViewModel())
}