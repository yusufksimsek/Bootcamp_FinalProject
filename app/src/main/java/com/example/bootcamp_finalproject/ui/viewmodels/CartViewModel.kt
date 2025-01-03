package com.example.bootcamp_finalproject.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bootcamp_finalproject.data.entity.Movie_Cart
import com.example.bootcamp_finalproject.data.repo.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(var moviesRepository: MoviesRepository) : ViewModel() {

    val moviesList = MutableLiveData<List<Movie_Cart>>()

    init {
        getMovieCart(userName = "yusuf_simsek")
    }

    fun deleteMovieCart(cartId: Int, userName: String){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                // Silme işlemi
                moviesRepository.deleteMovieCart(cartId, userName)

                // Silinen öğeyi listeden çıkarmak
                val updatedList = moviesList.value?.filter { it.cartId != cartId }
                moviesList.value = updatedList ?: listOf()
            } catch (e: Exception) {
                // Hata durumu
            }
        }
    }

    fun getMovieCart(userName: String) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val movieCartList = moviesRepository.getMovieCart(userName)
                moviesList.value = movieCartList
            } catch (e: Exception) {

            }
        }
    }
}

