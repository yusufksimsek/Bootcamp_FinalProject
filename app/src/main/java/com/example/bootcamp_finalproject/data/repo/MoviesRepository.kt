package com.example.bootcamp_finalproject.data.repo

import com.example.bootcamp_finalproject.data.datasource.MoviesDataSource
import com.example.bootcamp_finalproject.data.entity.Movie_Cart
import com.example.bootcamp_finalproject.data.entity.Movies

class MoviesRepository(var moviesDataSource: MoviesDataSource) {
    suspend fun loadMovies() : List<Movies> = moviesDataSource.loadMovies()
    suspend fun searchMovies(query: String): List<Movies> {
        val allMovies = loadMovies()
        return allMovies.filter { it.name.contains(query, ignoreCase = true) }
    }
    suspend fun addCart(
        name:String, image:String, price:Int, category: String, rating:Double,
        year:Int, director:String, description:String, orderAmount:Int, userName:String
    ) = moviesDataSource.addCart(
        name, image, price, category, rating, year, director, description, orderAmount, userName)

    suspend fun getMovieCart(userName: String): List<Movie_Cart> {
        return try {
            val movieCartList = moviesDataSource.getMovieCart(userName)
            movieCartList.ifEmpty { emptyList() }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun deleteMovieCart(cartId: Int, userName: String) = moviesDataSource.deleteMovieCart(cartId, userName)

}