package com.example.bootcamp_finalproject.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bootcamp_finalproject.data.entity.FavouriteMovie

@Dao
interface FavouriteMoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: FavouriteMovie)

    @Delete
    fun delete(movie: FavouriteMovie)

    @Query("SELECT * FROM favourite_movies")
    fun getAllFavouriteMovies(): LiveData<List<FavouriteMovie>>
}