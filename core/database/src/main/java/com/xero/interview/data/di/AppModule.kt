package com.xero.interview.data.di

import android.app.Application
import androidx.room.Room
import com.xero.interview.data.data.XeroDatabase
import com.xero.interview.data.data.repo.AccountRecordRepoImpl
import com.xero.interview.data.data.repo.BankAccountRepoImpl
import com.xero.interview.data.data.repo.TransactionRecordRepoImpl
import com.xero.interview.data.domain.repo.AccountRecordRepo
import com.xero.interview.data.domain.repo.BankAccountRepo
import com.xero.interview.data.domain.repo.TransactionRecordRepo
import com.xero.interview.utils.utils.Constants.MAIN_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesXeroDatabase(app: Application) = Room.databaseBuilder(
        app,
        XeroDatabase::class.java,
        MAIN_DATABASE
    ).build()

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
}