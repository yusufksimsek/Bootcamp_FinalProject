package com.example.bootcamp_finalproject.data.datasource

import com.example.bootcamp_finalproject.data.entity.Movies
import com.example.bootcamp_finalproject.retrofit.MoviesDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesDataSource(var moviesDao: MoviesDao) {
    suspend fun loadMovies() : List<Movies> = withContext(Dispatchers.IO){
        return@withContext moviesDao.getAllMovies().movies
    }
}