package com.example.bootcamp_finalproject.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bootcamp_finalproject.data.entity.Movies
import com.example.bootcamp_finalproject.data.repo.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    var moviesRepository : MoviesRepository) : ViewModel() {

    val moviesList = MutableLiveData<List<Movies>>()
    private var searchJob: Job? = null

    fun searchMovies(query: String) {
        searchJob?.cancel() // Önceki aramayı iptal et
        searchJob = CoroutineScope(Dispatchers.Main).launch {
            if (query.isBlank()) {
                // Eğer arama metni boşsa listeyi temizle
                moviesList.value = emptyList()
            } else {
                delay(300) // Debounce için gecikme
                val allMovies = moviesRepository.loadMovies()
                moviesList.value = allMovies.filter {
                    it.name.contains(query, ignoreCase = true)
                }
            }
        }
    }
}