package com.example.bootcamp_finalproject.data.datasource

import com.example.bootcamp_finalproject.data.entity.Movie_Cart
import com.example.bootcamp_finalproject.data.entity.Movies
import com.example.bootcamp_finalproject.retrofit.MoviesDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesDataSource(var moviesDao: MoviesDao) {
    suspend fun loadMovies(): List<Movies> = withContext(Dispatchers.IO) {
        return@withContext moviesDao.getAllMovies().movies
    }

    suspend fun addMovieCart(
        name: String, image: String, price: Int, category: String, rating: Double, year: Int,
        director: String, description: String, orderAmount: Int, userName: String
    ) {
            moviesDao.addCart(
                name, image, price, category, rating, year, director, description, orderAmount, userName
            )
    }

    suspend fun getMovieCart(userName: String): List<Movie_Cart> = withContext(Dispatchers.IO) {
        try {
            val response = moviesDao.getMovieCart(userName)
            return@withContext response.movie_cart ?: emptyList()
        } catch (e: Exception) {
            return@withContext emptyList()
        }
    }

    suspend fun deleteMovieCart(cartId: Int, userName: String) {
        moviesDao.deleteMovieCart(cartId, userName)
    }

}