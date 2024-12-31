package com.example.bootcamp_finalproject.data.repo

import com.example.bootcamp_finalproject.data.datasource.MoviesDataSource
import com.example.bootcamp_finalproject.data.entity.Movies

class MoviesRepository(var moviesDataSource: MoviesDataSource) {
    suspend fun loadMovies() : List<Movies> = moviesDataSource.loadMovies()

    suspend fun searchMovies(query: String): List<Movies> {
        val allMovies = loadMovies()
        return allMovies.filter { it.name.contains(query, ignoreCase = true) }
    }

}