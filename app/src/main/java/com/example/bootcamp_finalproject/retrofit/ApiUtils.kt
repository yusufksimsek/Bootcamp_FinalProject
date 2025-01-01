package com.example.bootcamp_finalproject.retrofit

import android.content.Context

class ApiUtils {
    companion object{
        val BASE_URL = "http://kasimadalan.pe.hu/"
        //http://kasimadalan.pe.hu/movies/getAllMovies.php

        fun getmoviesDao(context: Context) : MoviesDao {
            return RetrofitClient.getClient(BASE_URL, context).create(MoviesDao::class.java)
        }
    }
}