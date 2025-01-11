package com.example.bootcamp_finalproject.data.datasource

import androidx.lifecycle.LiveData
import com.example.bootcamp_finalproject.data.entity.favouritemovie.FavouriteMovie
import com.example.bootcamp_finalproject.data.local.FavouriteMoviesDao

class FavouriteMoviesDataSource(private val favouriteMoviesDao: FavouriteMoviesDao) {
    fun getAllFavouriteMovies(): LiveData<List<FavouriteMovie>> {
        return favouriteMoviesDao.getAllFavouriteMovies()
    }

    suspend fun insertFavouriteMovie(movie: FavouriteMovie) {
        favouriteMoviesDao.insert(movie)
    }

    suspend fun deleteFavouriteMovie(movie: FavouriteMovie) {
        favouriteMoviesDao.delete(movie)
    }
}