package com.xero.interview.bankrecmatchmaker

import android.os.Bundle
import android.util.Log
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
    lateinit var getBankAccountsUseCase: GetAllBankAccountsUseCase
//
//    @Inject
//    lateinit var insertAccountUseCase: InsertBankAccountUseCase
//
//    @Inject
//    lateinit var insertAccRecordUseCase: InsertAccountRecordUseCase
//
//    @Inject
//    lateinit var insertTransRecordUseCase: InsertTransactionRecordUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //initDatabaseValues()
        lifecycleScope.launch(Dispatchers.IO) {
            getBankAccountsUseCase().collectLatest {
                it.forEach { a ->
                    Log.e("---->", a.name)
                }
            }
        }
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

//    fun initDatabaseValues() {
//        lifecycleScope.launch(Dispatchers.IO) {
//            getBankAccountsUseCase().collectLatest {
//                if (it.isEmpty()) {
//                    bankAccounts.forEach { acc ->
//                        insertAccountUseCase(acc)
//                    }
//
//                    accountRecords.forEach { acc ->
//                        insertAccRecordUseCase(acc)
//                    }
//
//                    transactionRecords.forEach { trans ->
//                        insertTransRecordUseCase(trans)
//                    }
//                }
//            }
//        }
//    }
}
