package com.example.bootcamp_finalproject.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bootcamp_finalproject.data.entity.favourite_movie.FavouriteMovie
import com.example.bootcamp_finalproject.data.repo.FavouriteMoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    private val favouriteMoviesRepository: FavouriteMoviesRepository
) : ViewModel() {

    val favouriteMovies: LiveData<List<FavouriteMovie>> = favouriteMoviesRepository.getFavouriteMovies()

    fun removeFavouriteMovie(movie: FavouriteMovie) {   // delete fav movie
        viewModelScope.launch(Dispatchers.IO) {
            favouriteMoviesRepository.removeFavouriteMovie(movie)
        }
    }

    fun isMovieFavourite(movieId: Int): Boolean {   // Check the movie is favourite or not
        return favouriteMovies.value?.any { it.id == movieId } ?: false
    }

    fun toggleFavourite(movie: FavouriteMovie) {    // add or remove movie according fav or not
        viewModelScope.launch(Dispatchers.IO) {
            if (isMovieFavourite(movie.id)) {
                favouriteMoviesRepository.removeFavouriteMovie(movie)
            } else {
                favouriteMoviesRepository.addFavouriteMovie(movie)
            }
        }
    }

}