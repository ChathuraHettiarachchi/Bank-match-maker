package com.xero.interview.bankrecmatchmaker.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.xero.interview.account_records.navigation.accountRecordGraph
import com.xero.interview.account_records.navigation.navigateToAccountRecord
import com.xero.interview.find_matches.navigation.findMatchGraph
import com.xero.interview.find_matches.navigation.navigateToFindMatch
import com.xero.interview.home.navigation.HomeDestination
import com.xero.interview.home.navigation.homeGraph

@Composable
fun XeroNavHost(
    navController: NavHostController,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    startDestination: String = HomeDestination.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        homeGraph(navigateToRecords = { bankAccount ->
            navController.navigateToAccountRecord(
                bankAccount.toString()
            )
        }, nestedGraphs = {
            accountRecordGraph(
                onBack = onBackClick,
                navigateToFindMatches = { bankId, accountId ->
                    navController.navigateToFindMatch(
                        bankId.toString(),
                        accountId.toString()
                    )
                }
            )
            findMatchGraph(onBack = onBackClick)
        })
    }
}