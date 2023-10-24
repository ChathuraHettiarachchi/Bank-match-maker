package com.xero.interview.account_records.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xero.interview.data.domain.model.AccountRecord
import com.xero.interview.data.domain.model.BankAccount
import com.xero.interview.data.domain.use_case.account_record.GetAllAccountRecordsUseCase
import com.xero.interview.data.domain.use_case.bank_account.FindBankAccountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountRecordViewModel @Inject constructor(
    private val allAccountRecordsUseCase: GetAllAccountRecordsUseCase,
    private val findBankAccountUseCase: FindBankAccountUseCase
) :
    ViewModel() {

    var bankAccount = MutableStateFlow<BankAccount?>(null)
        private set

    var records = MutableStateFlow<List<AccountRecord>>(emptyList())
        private set

    fun loadAccountRecords(bankAccountId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            bankAccount.value = findBankAccountUseCase(bankAccountId)

            allAccountRecordsUseCase(bankAccountId).collectLatest {
                records.value = it
            }
        }
    }
}