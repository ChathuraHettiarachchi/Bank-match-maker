package com.xero.interview.find_matches.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xero.interview.data.domain.model.AccountRecord
import com.xero.interview.data.domain.model.TransactionRecord
import com.xero.interview.data.domain.use_case.account_record.FindAccountRecordUseCase
import com.xero.interview.data.domain.use_case.transaction_record.GetAllTransactionRecordsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FindMatchViewModel @Inject constructor(
    private val allTransactionRecordsUseCase: GetAllTransactionRecordsUseCase,
    private val findAccountRecordUseCase: FindAccountRecordUseCase
) :
    ViewModel() {

    var account = MutableStateFlow<AccountRecord?>(null)
        private set

    var records: MutableStateFlow<List<TransactionRecord>> =
        MutableStateFlow<List<TransactionRecord>>(emptyList())
        private set

    fun loadTransactions(bankAccountId: Long, accountId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            account.value = findAccountRecordUseCase(accountId)

            allTransactionRecordsUseCase(bankAccountId).collectLatest {
                records.value = it
            }
        }
    }
}