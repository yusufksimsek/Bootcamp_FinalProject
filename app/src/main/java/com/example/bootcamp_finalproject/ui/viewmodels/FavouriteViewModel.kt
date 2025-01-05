package com.example.bootcamp_finalproject.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bootcamp_finalproject.data.entity.FavouriteMovie
import com.example.bootcamp_finalproject.data.repo.FavouriteMoviesRepository
import com.example.bootcamp_finalproject.data.room.FavouriteMoviesDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    private val favouriteMoviesRepository: FavouriteMoviesRepository
) : ViewModel() {

    val favouriteMovies: LiveData<List<FavouriteMovie>> = favouriteMoviesRepository.getFavouriteMovies()

    fun addFavouriteMovie(movie: FavouriteMovie) {
        viewModelScope.launch(Dispatchers.IO) {
            favouriteMoviesRepository.addFavouriteMovie(movie)
        }
    }

    fun removeFavouriteMovie(movie: FavouriteMovie) {
        viewModelScope.launch(Dispatchers.IO) {
            favouriteMoviesRepository.removeFavouriteMovie(movie)
        }
    }

}