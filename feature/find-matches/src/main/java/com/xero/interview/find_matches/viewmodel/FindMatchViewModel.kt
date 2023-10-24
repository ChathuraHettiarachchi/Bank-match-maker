package com.xero.interview.find_matches.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xero.interview.common.helpers.isAMatch
import com.xero.interview.common.models.TransactionRecordModel
import com.xero.interview.data.domain.model.AccountRecord
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
) : ViewModel() {

    var amountToMatch = MutableStateFlow(0.0)
        private set

    var account = MutableStateFlow<AccountRecord?>(null)
        private set

    var records = MutableStateFlow<List<TransactionRecordModel>>(emptyList())
        private set

    fun loadTransactions(bankAccountId: Long, accountId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            val accountRecord = findAccountRecordUseCase(accountId)
            account.value = accountRecord
            amountToMatch.value = accountRecord.amount

            allTransactionRecordsUseCase(bankAccountId).collectLatest {
                records.value = it.map { i ->
                    TransactionRecordModel(
                        record = i,
                        isChecked = false,
                        isEnable = true,
                        isMatch = i.isAMatch(account.value!!)
                    )
                }
            }
        }
    }
}