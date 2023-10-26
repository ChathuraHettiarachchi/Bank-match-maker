package com.xero.interview.bankrecmatchmaker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.xero.interview.bankrecmatchmaker.navigation.XeroNavHost
import com.xero.interview.bankrecmatchmaker.ui.theme.XeroAndroidExercise2023Theme
import com.xero.interview.data.domain.use_case.bank_account.GetAllBankAccountsUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var allBankAccountsUseCase: GetAllBankAccountsUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDb()
        setContent {
            XeroAndroidExercise2023Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    XeroNavHost(navController = navController, { navController.popBackStack() })
                }
            }
        }
    }

    private fun initDb() {
        lifecycleScope.launch(Dispatchers.IO) {
            // I know this is bad, but sometimes the DB doesn't init with records for the home page
            allBankAccountsUseCase().collectLatest {
            }
        }
    }
}