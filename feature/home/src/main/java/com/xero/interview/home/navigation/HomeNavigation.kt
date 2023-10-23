package com.xero.interview.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.xero.interview.home.HomeRoute
import com.xero.interview.navigation.Screen
import com.xero.interview.navigation.XeroNavigationDestination

object HomeDestination : XeroNavigationDestination {
    override val route: String = Screen.Home.route
    override val destination: String = "${Screen.Home.route}_destination"
}

fun NavGraphBuilder.homeGraph(
    navigateToRecords: (Long) -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit
) {
    composable(route = HomeDestination.route) {
        HomeRoute(navigateToAccountRecords = navigateToRecords)
    }
    nestedGraphs()
}