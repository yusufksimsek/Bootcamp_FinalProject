package com.example.bootcamp_finalproject.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bootcamp_finalproject.ui.viewmodels.AuthViewModel

@Composable
fun PageTransition(authViewModel: AuthViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "loginScreen"){
        composable("mainScreen"){
            MainScreen(navController = navController, authViewModel = authViewModel)
        }
        composable("loginScreen"){
            LoginScreen(navController = navController, authViewModel = authViewModel)
        }
        composable("registerScreen"){
            RegisterScreen(navController = navController, authViewModel = authViewModel)
        }
    }
}