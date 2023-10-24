package com.xero.interview.find_matches.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xero.interview.common.helpers.isAMatch
import com.xero.interview.common.models.ErrorModel
import com.xero.interview.common.models.TransactionRecordModel
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
import kotlin.math.absoluteValue
import kotlin.math.sign

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

    var errorData = MutableStateFlow<ErrorModel>(ErrorModel())
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

    fun selectTransaction(record: TransactionRecord, isChecked: Boolean) {
        val _amountToMatch = (amountToMatch.value)
        val recAmount = if (record.amount < 0) record.amount else record.amount.absoluteValue
        var enabled = true

        if (isChecked) {
            val newAmountToMarch = (_amountToMatch + recAmount)
            amountToMatch.value = newAmountToMarch
            updateRecords(record = record, isEnabled = enabled)
        } else {
            val difference = _amountToMatch - (recAmount)
            val previousSign = _amountToMatch.sign
            val currentSign = difference.sign

            if (difference.absoluteValue > _amountToMatch.absoluteValue || (previousSign != currentSign && currentSign != 0.0)) {
                if (_amountToMatch == 0.0)
                    errorData.value =
                        ErrorModel(true, "You have already fulfilled the match criteria")
                else
                    errorData.value =
                        ErrorModel(true, "Selected value exceeds or maximize the match criteria")
            } else {
                if (difference == 0.0) {
                    amountToMatch.value = 0.0
                    enabled = false
                } else {
                    amountToMatch.value = difference
                }
                updateRecords(record, enabled)
            }
        }
    }

    private fun updateRecords(
        record: TransactionRecord,
        isEnabled: Boolean
    ) {
        records.value = records.value.map { item ->
            if (item.record.id == record.id) {
                item.copy(isChecked = !item.isChecked, isEnable = true)
            } else {
                item.copy(isEnable = if (item.isChecked) true else isEnabled)
            }
        }
    }


}