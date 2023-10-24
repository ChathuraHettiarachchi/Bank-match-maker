package com.xero.interview.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xero.interview.common.models.BankAccountModel
import com.xero.interview.data.domain.use_case.account_record.CountAccountRecordUseCase
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
    private val getSumBankAccountUseCase: GetSumBankAccountUseCase,
    private val countAccountRecordUseCase: CountAccountRecordUseCase
) : ViewModel() {
    var totalAmount: MutableStateFlow<Double> = MutableStateFlow(0.0)
        private set

    var bankAccounts: MutableStateFlow<List<BankAccountModel>> =
        MutableStateFlow<List<BankAccountModel>>(emptyList())
        private set

    init {
        loadBankAccounts()
    }

    private fun loadBankAccounts() {
        viewModelScope.launch(Dispatchers.IO) {
            val _accounts = mutableListOf<BankAccountModel>()
            backAccountsUseCase().collectLatest {
                it.forEach { acc ->
                    val _count = countAccountRecordUseCase(acc.id)
                    _accounts.add(BankAccountModel(acc, _count))
                }

                bankAccounts.value = _accounts
                totalAmount.value = getSumBankAccountUseCase.invoke()
            }
        }
    }
}