package com.example.bootcamp_finalproject.ui.screens.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bootcamp_finalproject.ui.screens.BottomBarPage
import com.example.bootcamp_finalproject.ui.screens.CartScreen
import com.example.bootcamp_finalproject.ui.screens.FavouritesScreen
import com.example.bootcamp_finalproject.ui.screens.LoginScreen
import com.example.bootcamp_finalproject.ui.screens.MainScreen
import com.example.bootcamp_finalproject.ui.screens.PersonScreen
import com.example.bootcamp_finalproject.ui.screens.RegisterScreen
import com.example.bootcamp_finalproject.ui.viewmodels.AuthViewModel
import com.example.bootcamp_finalproject.ui.viewmodels.MainViewModel

@Composable
fun PageTransition(
    authViewModel: AuthViewModel,
    selectedPage:String,
    mainViewModel: MainViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = selectedPage){
        composable("bottomBarPage"){
            BottomBarPage(navController = navController, mainViewModel = mainViewModel ,authViewModel = authViewModel)
        }
        composable("loginScreen"){
            LoginScreen(navController = navController, authViewModel = authViewModel)
        }
        composable("registerScreen"){
            RegisterScreen(navController = navController, authViewModel = authViewModel)
        }
        composable("mainScreen"){
            MainScreen(mainViewModel)
        }
        composable("favouritesScreen"){
            FavouritesScreen()
        }
        composable("cartScreen"){
            CartScreen()
        }
        composable("personScreen"){
            PersonScreen(authViewModel)
        }
    }
}