package com.xero.interview.account_records.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xero.interview.common.helpers.matchMake
import com.xero.interview.common.models.AccountRecordModel
import com.xero.interview.data.domain.model.AccountRecord
import com.xero.interview.data.domain.model.BankAccount
import com.xero.interview.data.domain.model.TransactionRecord
import com.xero.interview.data.domain.use_case.account_record.GetAllAccountRecordsUseCase
import com.xero.interview.data.domain.use_case.bank_account.FindBankAccountUseCase
import com.xero.interview.data.domain.use_case.transaction_record.GetAllTransactionRecordsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountRecordViewModel @Inject constructor(
    private val allAccountRecordsUseCase: GetAllAccountRecordsUseCase,
    private val findBankAccountUseCase: FindBankAccountUseCase,
    private val allTransactionRecordsUseCase: GetAllTransactionRecordsUseCase
) :
    ViewModel() {

    private var _bankAccountId: Long? = null
    private var transactions = listOf<TransactionRecord>()
    private var records = listOf<AccountRecord>()

    var bankAccount = MutableStateFlow<BankAccount?>(null)
        private set

    var matchedRecords = MutableStateFlow<List<AccountRecordModel>>(emptyList())
        private set

    fun loadAccountRecords(bankAccountId: Long) {
        _bankAccountId = bankAccountId

        viewModelScope.launch(Dispatchers.IO) {
            bankAccount.value = findBankAccountUseCase(bankAccountId)

            allAccountRecordsUseCase(bankAccountId).collectLatest {
                records = it
                matchMakeRecords(bankAccountId)
            }
        }
    }

    private fun matchMakeRecords(bankAccountId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            allTransactionRecordsUseCase(bankAccountId).collectLatest {
                transactions = it
                matchedRecords.value = records.matchMake(transactionRecords = transactions)
            }
        }
    }
}