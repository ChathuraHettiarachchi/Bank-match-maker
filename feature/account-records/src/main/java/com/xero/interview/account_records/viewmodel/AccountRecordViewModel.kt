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

/**
 * AccountRecordViewModel will load all account records that needs to matched in the back account.
 * it will show already available matched in the first show
 * @param allAccountRecordsUseCase use to get all account records from db based on bank id
 * @param findBankAccountUseCase use to get the bank account from id
 * @param allTransactionRecordsUseCase get all transactions from bank id
 */
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

    /**
     * load all account records, on the fly it will match with transaction records with
     * matching amount
     * @param bankAccountId bank account id
     */
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