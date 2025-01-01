package com.example.bootcamp_finalproject.ui.screens.navigation

import MainScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bootcamp_finalproject.data.entity.Movies
import com.example.bootcamp_finalproject.ui.screens.BottomBarPage
import com.example.bootcamp_finalproject.ui.screens.CartScreen
import com.example.bootcamp_finalproject.ui.screens.FavouritesScreen
import com.example.bootcamp_finalproject.ui.screens.MovieDetailScreen
import com.example.bootcamp_finalproject.ui.screens.authorization.LoginScreen
import com.example.bootcamp_finalproject.ui.screens.authorization.RegisterScreen
import com.example.bootcamp_finalproject.ui.screens.SearchScreen
import com.example.bootcamp_finalproject.ui.viewmodels.AuthViewModel
import com.example.bootcamp_finalproject.ui.viewmodels.MainViewModel
import com.example.bootcamp_finalproject.ui.viewmodels.SearchViewModel
import com.google.gson.Gson

@Composable
fun PageTransition(
    authViewModel: AuthViewModel,
    selectedPage:String,
    mainViewModel: MainViewModel,
    searchViewModel: SearchViewModel
    ) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = selectedPage){
        composable("bottomBarPage"){
            BottomBarPage(navController = navController,
                mainViewModel = mainViewModel,
                authViewModel = authViewModel,
                searchViewModel = searchViewModel
                )
        }
        composable("loginScreen"){
            LoginScreen(navController = navController, authViewModel = authViewModel)
        }
        composable("registerScreen"){
            RegisterScreen(navController = navController, authViewModel = authViewModel)
        }
        composable("mainScreen"){
            MainScreen(navController = navController, mainViewModel = mainViewModel)
        }
        composable("favouritesScreen"){
            FavouritesScreen()
        }
        composable("cartScreen"){
            CartScreen()
        }
        composable("searchScreen"){
            SearchScreen(navController = navController, searchViewModel = searchViewModel)
        }
        composable("movieDetailScreen/{movie}",
            arguments = listOf(navArgument("movie") { type = NavType.StringType } )
        ){
            val json = it.arguments?.getString("movie")
            val movieObject = Gson().fromJson(json, Movies::class.java)
            MovieDetailScreen(pullingMovie = movieObject)
        }
    }
}