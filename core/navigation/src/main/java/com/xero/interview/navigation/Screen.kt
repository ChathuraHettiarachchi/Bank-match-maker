package com.xero.interview.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object AccountRecord : Screen("account_record")
    object TransactionRecord : Screen("transaction_record")
}
