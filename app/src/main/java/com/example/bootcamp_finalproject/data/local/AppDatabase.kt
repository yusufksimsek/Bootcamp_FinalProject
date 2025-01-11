package com.example.bootcamp_finalproject.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bootcamp_finalproject.data.entity.favouritemovie.FavouriteMovie

@Database(entities = [FavouriteMovie::class], version = 2) // Specifies the database schema
abstract class AppDatabase : RoomDatabase() {
    abstract fun favouriteMoviesDao(): FavouriteMoviesDao
}