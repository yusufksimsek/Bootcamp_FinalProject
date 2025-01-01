package com.example.bootcamp_finalproject.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.bootcamp_finalproject.data.repo.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(var moviesRepository: MoviesRepository) :
    ViewModel() {
    fun addCart(
        name: String,
        image: String,
        price: Int,
        category: String,
        rating: Double,
        year: Int,
        director: String,
        description: String,
        orderAmount: Int,
        userName: String
    ) {
        CoroutineScope(Dispatchers.Main).launch {
            moviesRepository.addCart(
                name,
                image,
                price,
                category,
                rating,
                year,
                director,
                description,
                orderAmount,
                userName
            )
        }
    }
}