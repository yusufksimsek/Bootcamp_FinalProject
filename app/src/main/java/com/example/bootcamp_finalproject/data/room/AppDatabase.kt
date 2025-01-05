package com.example.bootcamp_finalproject.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bootcamp_finalproject.data.entity.FavouriteMovie

@Database(entities = [FavouriteMovie::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favouriteMoviesDao(): FavouriteMoviesDao
}