package com.example.bootcamp_finalproject.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun PageTransition() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "mainScreen"){
        composable("mainScreen"){
            MainScreen(navController = navController)
        }
        composable("loginScreen"){
            LoginScreen()
        }
        composable("registerScreen"){
            RegisterScreen()
        }
    }
}