package com.xero.interview.data.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.xero.interview.data.data.XeroDatabase
import com.xero.interview.data.data.repo.AccountRecordRepoImpl
import com.xero.interview.data.data.repo.BankAccountRepoImpl
import com.xero.interview.data.data.repo.TransactionRecordRepoImpl
import com.xero.interview.data.domain.repo.AccountRecordRepo
import com.xero.interview.data.domain.repo.BankAccountRepo
import com.xero.interview.data.domain.repo.TransactionRecordRepo
import com.xero.interview.utils.Constant
import com.xero.interview.utils.utils.Constants.MAIN_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private lateinit var database: XeroDatabase

    @Provides
    @Singleton
    fun providesXeroDatabase(app: Application, callback: RoomDatabase.Callback): XeroDatabase {
        database = Room.databaseBuilder(
            app,
            XeroDatabase::class.java,
            MAIN_DATABASE
        ).addCallback(callback).build()

        return database
    }

    @Provides
    fun providesRoomDatabaseCallback() = object : RoomDatabase.Callback() {
        val customScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            customScope.launch {
                Constant.bankAccounts.forEach {
                    database.bankAccountDao.insertAccount(it)
                }
                Constant.accountRecords.forEach {
                    database.accountRecordDao.insertRecord(it)
                }
                Constant.transactionRecords.forEach {
                    database.transactionRecordDao.insertRecord(it)
                }
            }
        }
    }

    @Provides
    @Singleton
    fun providesBankAccountRepo(db: XeroDatabase): BankAccountRepo {
        return BankAccountRepoImpl(db.bankAccountDao)
    }

    @Provides
    @Singleton
    fun providesAccountRecordRepo(db: XeroDatabase): AccountRecordRepo {
        return AccountRecordRepoImpl(db.accountRecordDao)
    }

    @Provides
    @Singleton
    fun providesTransactionRecordRepo(db: XeroDatabase): TransactionRecordRepo {
        return TransactionRecordRepoImpl(db.transactionRecordDao)
    }

    @Singleton
    @Provides
    fun provideDispatchers(): CoroutineDispatcher = Dispatchers.IO
}