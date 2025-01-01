package com.example.bootcamp_finalproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.bootcamp_finalproject.ui.screens.navigation.PageTransition
import com.example.bootcamp_finalproject.ui.theme.Bootcamp_FinalProjectTheme
import com.example.bootcamp_finalproject.ui.viewmodels.AuthViewModel
import com.example.bootcamp_finalproject.ui.viewmodels.MainViewModel
import com.example.bootcamp_finalproject.ui.viewmodels.MovieDetailsViewModel
import com.example.bootcamp_finalproject.ui.viewmodels.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val authViewModel : AuthViewModel by viewModels()
        val mainViewModel : MainViewModel by viewModels()
        val searchViewModel : SearchViewModel by viewModels()
        val movieDetailsViewModel: MovieDetailsViewModel by viewModels()
        setContent {
            Bootcamp_FinalProjectTheme {
                val isBottomBarVisible = remember { mutableStateOf(true) }
                PageTransition(
                    authViewModel = authViewModel,
                    mainViewModel = mainViewModel,
                    searchViewModel = searchViewModel,
                    isBottomBarVisible = isBottomBarVisible,
                    movieDetailsViewModel = movieDetailsViewModel,
                    selectedPage = "loginScreen")
            }
        }
    }
}