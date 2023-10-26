package com.xero.interview.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xero.interview.common.models.BankAccountModel
import com.xero.interview.data.domain.use_case.account_record.CountAccountRecordUseCase
import com.xero.interview.data.domain.use_case.bank_account.GetAllBankAccountsUseCase
import com.xero.interview.data.domain.use_case.bank_account.GetSumBankAccountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * HomeViewModel, the state holder for home screen
 * @param backAccountsUseCase to get all back accounts
 * @param getSumBankAccountUseCase give sum of all account
 * @param countAccountRecordUseCase count need to match records
 * @param dispatcher, injected by DI
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val backAccountsUseCase: GetAllBankAccountsUseCase,
    private val getSumBankAccountUseCase: GetSumBankAccountUseCase,
    private val countAccountRecordUseCase: CountAccountRecordUseCase,
    private val dispatcher: CoroutineDispatcher,
) : ViewModel() {

    var totalAmount = MutableStateFlow(0.0)
        private set

    var bankAccounts = MutableStateFlow<List<BankAccountModel>>(emptyList())
        private set

    init {
        loadBankAccounts()
    }

    /**
     * Will populate both total amount and all the bank accounts
     */
    fun loadBankAccounts() {
        val _accounts = mutableListOf<BankAccountModel>()
        viewModelScope.launch(dispatcher) {
            backAccountsUseCase().collectLatest {
                _accounts.clear()
                it.forEach { acc ->
                    //delay(100)
                    val _count = countAccountRecordUseCase(acc.id.toInt())
                    _accounts.add(BankAccountModel(acc, _count))
                }
                bankAccounts.value = _accounts
                totalAmount.value = getSumBankAccountUseCase() ?: 0.0
            }
        }
    }
}