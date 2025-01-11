package com.example.bootcamp_finalproject.data.repo

import com.example.bootcamp_finalproject.data.datasource.MoviesDataSource
import com.example.bootcamp_finalproject.data.entity.moviecart.MovieCart
import com.example.bootcamp_finalproject.data.entity.movies.Movies

// Acts as a bridge between the data layer (MoviesDataSource) and the rest of the application.
class MoviesRepository(var moviesDataSource: MoviesDataSource) {
    suspend fun loadMovies() : List<Movies> = moviesDataSource.loadMovies()
    suspend fun searchMovies(query: String): List<Movies> {
        return moviesDataSource.searchMovies(query)
    }
    suspend fun addMovieCart(
        name:String, image:String, price:Int, category: String, rating:Double,
        year:Int, director:String, description:String, orderAmount:Int, userName:String
    ) = moviesDataSource.addMovieCart(
        name, image, price, category, rating, year, director, description, orderAmount, userName)

    suspend fun getMovieCart(userName: String): List<MovieCart> {
        return try {
            val movieCartList = moviesDataSource.getMovieCart(userName)
            movieCartList.ifEmpty { emptyList() }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun deleteMovieCart(cartId: Int, userName: String) = moviesDataSource.deleteMovieCart(cartId, userName)

}