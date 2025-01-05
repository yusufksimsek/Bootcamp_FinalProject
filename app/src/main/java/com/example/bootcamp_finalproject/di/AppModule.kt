package com.example.bootcamp_finalproject.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.bootcamp_finalproject.data.datasource.FavouriteMoviesDataSource
import com.example.bootcamp_finalproject.data.datasource.FirebaseAuthDataSource
import com.example.bootcamp_finalproject.data.datasource.MoviesDataSource
import com.example.bootcamp_finalproject.data.repo.AuthRepository
import com.example.bootcamp_finalproject.data.repo.FavouriteMoviesRepository
import com.example.bootcamp_finalproject.data.repo.MoviesRepository
import com.example.bootcamp_finalproject.data.local.AppDatabase
import com.example.bootcamp_finalproject.data.local.FavouriteMoviesDao
import com.example.bootcamp_finalproject.retrofit.ApiUtils
import com.example.bootcamp_finalproject.retrofit.MoviesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Provides
    @Singleton
    fun provideFavouriteMoviesDao(appDatabase: AppDatabase): FavouriteMoviesDao {
        return appDatabase.favouriteMoviesDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).fallbackToDestructiveMigration()
         .build()
    }

    @Provides
    @Singleton
    fun provideFavouriteMoviesDataSource(favouriteMoviesDao: FavouriteMoviesDao): FavouriteMoviesDataSource {
        return FavouriteMoviesDataSource(favouriteMoviesDao)
    }

    @Provides
    @Singleton
    fun provideFavouriteMoviesRepository(dataSource: FavouriteMoviesDataSource): FavouriteMoviesRepository {
        return FavouriteMoviesRepository(dataSource)
    }


}