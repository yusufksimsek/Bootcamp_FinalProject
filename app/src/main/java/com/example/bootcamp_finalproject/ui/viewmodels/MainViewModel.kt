package com.example.bootcamp_finalproject.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bootcamp_finalproject.data.entity.Movies
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

    fun loadMovies() {
        CoroutineScope(Dispatchers.Main).launch {
            moviesList.value = moviesRepository.loadMovies()
        }
    }


}