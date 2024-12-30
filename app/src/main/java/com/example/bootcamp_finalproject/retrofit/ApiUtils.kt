package com.example.bootcamp_finalproject.retrofit

class ApiUtils {
    companion object{
        val BASE_URL = "http://kasimadalan.pe.hu/"
        //http://kasimadalan.pe.hu/movies/getAllMovies.php

        fun getmoviesDao() : MoviesDao {
            return RetrofitClient.getClient(BASE_URL).create(MoviesDao::class.java)
        }
    }
}