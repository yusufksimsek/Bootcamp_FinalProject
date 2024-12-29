package com.example.bootcamp_finalproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.bootcamp_finalproject.ui.screens.PageTransition
import com.example.bootcamp_finalproject.ui.theme.Bootcamp_FinalProjectTheme
import com.example.bootcamp_finalproject.ui.viewmodels.AuthViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val authViewModel : AuthViewModel by viewModels()
        setContent {
            Bootcamp_FinalProjectTheme {
                PageTransition(authViewModel = authViewModel)
            }
        }
    }
}