package com.example.bootcamp_finalproject.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bootcamp_finalproject.data.entity.Movie_Cart
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

    fun deleteMovieCart(cartId: Int, userName: String){
        CoroutineScope(Dispatchers.Main).launch {
            moviesRepository.deleteMovieCart(cartId, userName)
            getMovieCart(userName = "yusuf_simsek")
        }
    }

    fun getMovieCart(userName:String){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val movieCartList = moviesRepository.getMovieCart(userName)
                moviesList.value = movieCartList
            }catch (e:Exception){ }
        }
    }
}