package com.example.bootcamp_finalproject.data.datasource

import androidx.lifecycle.LiveData
import com.example.bootcamp_finalproject.data.entity.favourite_movie.FavouriteMovie
import com.example.bootcamp_finalproject.data.room.FavouriteMoviesDao

class FavouriteMoviesDataSource(private val favouriteMoviesDao: FavouriteMoviesDao) {
    fun getAllFavouriteMovies(): LiveData<List<FavouriteMovie>> {
        return favouriteMoviesDao.getAllFavouriteMovies()
    }

    fun insertFavouriteMovie(movie: FavouriteMovie) {
        favouriteMoviesDao.insert(movie)
    }

    fun deleteFavouriteMovie(movie: FavouriteMovie) {
        favouriteMoviesDao.delete(movie)
    }
}