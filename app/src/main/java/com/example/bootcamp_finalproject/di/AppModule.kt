package com.example.bootcamp_finalproject.di

import android.app.Application
import android.content.Context
import com.example.bootcamp_finalproject.data.datasource.FirebaseAuthDataSource
import com.example.bootcamp_finalproject.data.datasource.MoviesDataSource
import com.example.bootcamp_finalproject.data.repo.AuthRepository
import com.example.bootcamp_finalproject.data.repo.MoviesRepository
import com.example.bootcamp_finalproject.retrofit.ApiUtils
import com.example.bootcamp_finalproject.retrofit.MoviesDao
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
    fun provideAuthRepository() : AuthRepository {
        return AuthRepository(FirebaseAuthDataSource())
    }

    @Provides
    @Singleton
    fun provideMoviesRepository(moviesDataSource: MoviesDataSource) : MoviesRepository {
        return MoviesRepository(moviesDataSource)
    }

    @Provides
    @Singleton
    fun provideMoviesDataSource(moviesDao: MoviesDao) : MoviesDataSource{
        return MoviesDataSource(moviesDao)
    }

    @Provides
    @Singleton
    fun provideMoviesDao(context: Context) : MoviesDao {
        return ApiUtils.getmoviesDao(context)
    }

    @Provides
    @Singleton
    fun provideApplicationContext(application: Application): Context {
        return application.applicationContext
    }

}