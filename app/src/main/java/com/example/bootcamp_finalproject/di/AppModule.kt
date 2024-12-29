package com.example.bootcamp_finalproject.di

import com.example.bootcamp_finalproject.data.datasource.FirebaseAuthDataSource
import com.example.bootcamp_finalproject.data.repo.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideAuthRepository(): AuthRepository {
        return AuthRepository(FirebaseAuthDataSource())
    }
}
