package com.xero.interview.data.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.xero.interview.data.data.dao.AccountRecordDao
import com.xero.interview.data.data.dao.BankAccountDao
import com.xero.interview.data.data.dao.TransactionRecordDao
import com.xero.interview.data.domain.model.AccountRecord
import com.xero.interview.data.domain.model.BankAccount
import com.xero.interview.data.domain.model.TransactionRecord
import com.xero.interview.utils.Converters

@Database(
    entities = [BankAccount::class, AccountRecord::class, TransactionRecord::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class XeroDatabase : RoomDatabase() {
    abstract val bankAccountDao: BankAccountDao
    abstract val accountRecordDao: AccountRecordDao
    abstract val transactionRecordDao: TransactionRecordDao
}