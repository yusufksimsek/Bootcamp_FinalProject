package com.example.bootcamp_finalproject.data.datasource

import com.example.bootcamp_finalproject.data.entity.moviecart.MovieCart
import com.example.bootcamp_finalproject.data.entity.movies.Movies
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

    suspend fun getMovieCart(userName: String): List<MovieCart> = withContext(Dispatchers.IO) {
        try {
            val response = moviesDao.getMovieCart(userName)
            return@withContext response.movie_cart ?: emptyList() // MovieList should be nullable
        } catch (e: Exception) {
            return@withContext emptyList()
        }
    }

    suspend fun deleteMovieCart(cartId: Int, userName: String) {
        moviesDao.deleteMovieCart(cartId, userName)
    }

    suspend fun searchMovies(query: String): List<Movies> = withContext(Dispatchers.IO) {
        val allMovies = moviesDao.getAllMovies().movies
        return@withContext allMovies.filter { it.name.contains(query, ignoreCase = true) }
    }

    suspend fun getMoviesByCategory(category: String): List<Movies> = withContext(Dispatchers.IO) {
        val allMovies = moviesDao.getAllMovies().movies
        return@withContext allMovies.filter { it.category.equals(category, ignoreCase = true) }
    }

}