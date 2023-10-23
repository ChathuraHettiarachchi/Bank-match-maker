package com.xero.interview.find_matches.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.xero.interview.find_matches.FindMatchRoute
import com.xero.interview.navigation.Screen
import com.xero.interview.navigation.XeroNavigationDestination

object FindMatchDestination : XeroNavigationDestination {
    override val route: String = "${Screen.TransactionRecord.route}/{bankAccountId}/{accountId}"
    override val destination: String = "${Screen.TransactionRecord.route}_destination"
    const val bankAccountId = "bankAccountId"
    const val accountId = "accountId"
}

fun NavController.navigateToFindMatch(bankAccountId: String, accountId: String) {
    this.navigate("${Screen.TransactionRecord.route}/$bankAccountId/$accountId")
}

fun NavGraphBuilder.findMatchGraph(
    onBack: () -> Unit
) {
    composable(
        route = FindMatchDestination.route,
        arguments = listOf(navArgument(FindMatchDestination.bankAccountId) {
            type = NavType.StringType
        }, navArgument(FindMatchDestination.accountId) {
            type = NavType.StringType
        })
    ) { backStackEntry ->
        val bankAccountId =
            (backStackEntry.arguments?.getString(FindMatchDestination.bankAccountId))?.toLong() ?: 0
        val accountId =
            (backStackEntry.arguments?.getString(FindMatchDestination.accountId))?.toLong() ?: 0
        FindMatchRoute(bankAccountId = bankAccountId, accountId = accountId, onBackClick = onBack)

    }
}
