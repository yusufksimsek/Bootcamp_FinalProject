package com.example.bootcamp_finalproject.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bootcamp_finalproject.data.entity.moviecart.MovieCart
import com.example.bootcamp_finalproject.data.repo.MoviesRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(var moviesRepository: MoviesRepository) : ViewModel() {

    val moviesList = MutableLiveData<List<MovieCart>>()

    init {
        getMovieCart(userName = FirebaseAuth.getInstance().currentUser?.email.toString())
    }

    fun deleteMovieCart(cartId: Int, userName: String, onFailure: () -> Unit){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                moviesRepository.deleteMovieCart(cartId, userName)
                val updatedList = moviesList.value?.filter { it.cartId != cartId }
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
                moviesList.value = movieCartList
            } catch (e: Exception) {
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