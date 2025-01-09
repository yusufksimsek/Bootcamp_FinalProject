package com.example.bootcamp_finalproject.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bootcamp_finalproject.data.entity.movie_cart.Movie_Cart
import com.example.bootcamp_finalproject.data.repo.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(var moviesRepository: MoviesRepository) : ViewModel() {

    val moviesList = MutableLiveData<List<Movie_Cart>>()

    init {
        getMovieCart(userName = "yusuf_simsek")
    }

    fun deleteMovieCart(cartId: Int, userName: String,onSuccess: () -> Unit, onFailure: () -> Unit){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                moviesRepository.deleteMovieCart(cartId, userName)
                val updatedList = moviesList.value?.filter { it.cartId != cartId }
                onSuccess()
                moviesList.value = updatedList ?: listOf()
            } catch (e: Exception) {
                onFailure()
            }
        }
    }

    fun getMovieCart(userName: String) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val movieCartList = moviesRepository.getMovieCart(userName)
                if (movieCartList.isEmpty()) {
                    // Sepet boşsa yapılacaklar
                }
                moviesList.value = movieCartList
            } catch (e: Exception) {
                // Hata durumunda yapılacaklar
                moviesList.value = emptyList()
            }
        }
    }

    fun addMovieCart(
        name: String, image: String, price: Int, category: String, rating: Double, year: Int,
        director: String, description: String, orderAmount: Int, userName: String,
        onSuccess: () -> Unit,
    ) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val movieCartList = moviesRepository.getMovieCart(userName)

                // Film zaten sepette var mı kontrol et
                val existingMovie = movieCartList.find { it.name == name }

                if (existingMovie != null) {
                    // Film zaten sepette, önce sil, sonra yeni miktarla ekle
                    moviesRepository.deleteMovieCart(existingMovie.cartId, userName)
                    val newAmount = existingMovie.orderAmount + orderAmount
                    moviesRepository.addMovieCart(
                        name, image, price, category, rating, year,
                        director, description, newAmount, userName
                    )
                } else {
                    // Film yoksa, doğrudan ekle
                    moviesRepository.addMovieCart(
                        name, image, price, category, rating, year,
                        director, description, orderAmount, userName
                    )
                }

                onSuccess()
                getMovieCart(userName)
            } catch (e: Exception) {

            }
        }
    }
}