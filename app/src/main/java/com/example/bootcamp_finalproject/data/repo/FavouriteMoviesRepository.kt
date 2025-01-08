package com.example.bootcamp_finalproject.data.repo

import androidx.lifecycle.LiveData
import com.example.bootcamp_finalproject.data.datasource.FavouriteMoviesDataSource
import com.example.bootcamp_finalproject.data.entity.favourite_movie.FavouriteMovie

class FavouriteMoviesRepository(private val dataSource: FavouriteMoviesDataSource) {
    fun getFavouriteMovies(): LiveData<List<FavouriteMovie>> {
        return dataSource.getAllFavouriteMovies()
    }

    suspend fun addFavouriteMovie(movie: FavouriteMovie) {
        dataSource.insertFavouriteMovie(movie)
    }

    suspend fun removeFavouriteMovie(movie: FavouriteMovie) {
        dataSource.deleteFavouriteMovie(movie)
    }
}