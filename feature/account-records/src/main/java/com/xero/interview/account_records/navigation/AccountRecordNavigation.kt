package com.xero.interview.account_records.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.xero.interview.account_records.AccountRecordRoute
import com.xero.interview.navigation.Screen
import com.xero.interview.navigation.XeroNavigationDestination

object AccountRecordDestination : XeroNavigationDestination {
    override val route: String = "${Screen.AccountRecord.route}/{bankAccountId}"
    override val destination: String = "${Screen.AccountRecord.route}_destination"
    const val bankAccountId = "bankAccountId"
}

fun NavController.navigateToAccountRecord(bankAccountId: String) {
    this.navigate("${Screen.AccountRecord.route}/${bankAccountId}")
}

fun NavGraphBuilder.accountRecordGraph(
    onBack: () -> Unit,
    navigateToFindMatches: (Long, Long) -> Unit
) {
    composable(
        route = AccountRecordDestination.route,
        arguments = listOf(navArgument(AccountRecordDestination.bankAccountId) {
            type = NavType.StringType
        })
    ) { backStackEntry ->
        AccountRecordRoute(
            bankAccountId = (backStackEntry.arguments?.getString(
                AccountRecordDestination.bankAccountId
            ))?.toLong() ?: 0, onBackClick = onBack, navigateToFindMatches = navigateToFindMatches
        )
    }
}
