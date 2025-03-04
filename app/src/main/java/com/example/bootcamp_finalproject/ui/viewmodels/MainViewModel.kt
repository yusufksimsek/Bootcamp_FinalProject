package com.example.bootcamp_finalproject.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bootcamp_finalproject.data.entity.movies.Movies
import com.example.bootcamp_finalproject.data.repo.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    var moviesRepository : MoviesRepository) : ViewModel(){

    val moviesList = MutableLiveData<List<Movies>>()

    init {
        loadMovies()
    }

    fun loadMovies() {    // get all movies
        CoroutineScope(Dispatchers.Main).launch {
            moviesList.value = moviesRepository.loadMovies()
        }
    }

    fun getMoviesByCategory(category: String): LiveData<List<Movies>> {
        val filteredMovies = MutableLiveData<List<Movies>>()
        CoroutineScope(Dispatchers.Main).launch {
            filteredMovies.value = moviesRepository.getMoviesByCategory(category)
        }
        return filteredMovies
    }



}