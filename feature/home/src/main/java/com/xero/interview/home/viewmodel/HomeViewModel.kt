package com.xero.interview.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xero.interview.data.domain.model.BankAccount
import com.xero.interview.data.domain.use_case.bank_account.GetAllBankAccountsUseCase
import com.xero.interview.data.domain.use_case.bank_account.GetSumBankAccountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val backAccountsUseCase: GetAllBankAccountsUseCase,
    private val getSumBankAccountUseCase: GetSumBankAccountUseCase
) : ViewModel() {
    var totalAmount: MutableStateFlow<Double> = MutableStateFlow(0.0)
        private set

    var bankAccounts: MutableStateFlow<List<BankAccount>> =
        MutableStateFlow<List<BankAccount>>(emptyList())
        private set

    init {
        loadBankAccounts()
    }

    private fun loadBankAccounts() {
        viewModelScope.launch(Dispatchers.IO) {
            totalAmount.value = getSumBankAccountUseCase()

            backAccountsUseCase().collectLatest {
                bankAccounts.value = it
            }
        }
    }
}