package com.xero.interview.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xero.interview.data.domain.model.BankAccount
import com.xero.interview.data.domain.use_case.bank_account.GetAllBankAccountsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val backAccountsUseCase: GetAllBankAccountsUseCase) :
    ViewModel() {
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
            backAccountsUseCase().collectLatest { accounts ->
                val _accounts = mutableListOf<BankAccount>()
                accounts.forEach {
                    totalAmount.value += it.appBalance
                    _accounts.add(it)
                }

                bankAccounts.value = _accounts
            }
        }
    }
}