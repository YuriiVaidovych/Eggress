package com.yuriivaidovych.eggressapp.di

import com.yuriivaidovych.eggressapp.data.AuthRepositoryImpl
import com.yuriivaidovych.eggressapp.data.HabitRepositoryImpl
import com.yuriivaidovych.eggressapp.domain.AuthRepository
import com.yuriivaidovych.eggressapp.domain.HabitRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindHabitRepository(
        habitRepositoryImpl: HabitRepositoryImpl
    ): HabitRepository

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository
}