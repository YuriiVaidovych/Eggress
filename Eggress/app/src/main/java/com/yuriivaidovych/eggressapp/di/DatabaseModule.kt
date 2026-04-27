package com.yuriivaidovych.eggressapp.di

import android.content.Context
import com.yuriivaidovych.eggressapp.data.AppDatabase
import com.yuriivaidovych.eggressapp.data.AppDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        //окремий Scope для бази даних, щоб він не залежав від екранів
        val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
        return AppDatabase.getDatabase(context, applicationScope)
    }

    @Provides
    @Singleton
    fun provideAppDao(database: AppDatabase): AppDao {
        return database.appDao()
    }
}