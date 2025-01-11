package com.example.bootcamp_finalproject.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bootcamp_finalproject.data.entity.favouritemovie.FavouriteMovie

@Dao
interface FavouriteMoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: FavouriteMovie)

    @Delete
    suspend fun delete(movie: FavouriteMovie)

    @Query("SELECT * FROM favourite_movies")
    fun getAllFavouriteMovies(): LiveData<List<FavouriteMovie>>
}