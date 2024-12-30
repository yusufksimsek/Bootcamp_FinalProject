package com.example.bootcamp_finalproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.bootcamp_finalproject.ui.screens.navigation.PageTransition
import com.example.bootcamp_finalproject.ui.theme.Bootcamp_FinalProjectTheme
import com.example.bootcamp_finalproject.ui.viewmodels.AuthViewModel
import com.example.bootcamp_finalproject.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val authViewModel : AuthViewModel by viewModels()
        val mainViewModel : MainViewModel by viewModels()
        setContent {
            Bootcamp_FinalProjectTheme {
                PageTransition(
                    authViewModel = authViewModel,
                    mainViewModel = mainViewModel,
                    selectedPage = "loginScreen",
                    )
            }
        }
    }
}