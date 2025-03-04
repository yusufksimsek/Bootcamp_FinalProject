package com.example.bootcamp_finalproject.ui.screens.navigation

import MainScreen
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bootcamp_finalproject.data.entity.movies.Movies
import com.example.bootcamp_finalproject.ui.screens.authorization.AuthenticationScreen
import com.example.bootcamp_finalproject.ui.screens.BottomBarPage
import com.example.bootcamp_finalproject.ui.screens.CartScreen
import com.example.bootcamp_finalproject.ui.screens.FavouritesScreen
import com.example.bootcamp_finalproject.ui.screens.MovieDetailScreen
import com.example.bootcamp_finalproject.ui.screens.authorization.LoginScreen
import com.example.bootcamp_finalproject.ui.screens.authorization.RegisterScreen
import com.example.bootcamp_finalproject.ui.screens.SearchScreen
import com.example.bootcamp_finalproject.ui.viewmodels.AuthViewModel
import com.example.bootcamp_finalproject.ui.viewmodels.CartViewModel
import com.example.bootcamp_finalproject.ui.viewmodels.FavouriteViewModel
import com.example.bootcamp_finalproject.ui.viewmodels.MainViewModel
import com.example.bootcamp_finalproject.ui.viewmodels.SearchViewModel
import com.google.gson.Gson

@Composable
fun PageTransition(
    authViewModel: AuthViewModel,
    selectedPage:String,
    mainViewModel: MainViewModel,
    searchViewModel: SearchViewModel,
    cartViewModel: CartViewModel,
    favouriteViewModel: FavouriteViewModel,
    isBottomBarVisible: MutableState<Boolean>
    ) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = selectedPage){
        composable("bottomBarPage"){
            BottomBarPage(navController = navController,
                mainViewModel = mainViewModel,
                authViewModel = authViewModel,
                searchViewModel = searchViewModel,
                cartViewModel = cartViewModel,
                favouriteViewModel = favouriteViewModel
                )
        }
        composable("authenticationScreen"){
            AuthenticationScreen(navController = navController, authViewModel = authViewModel)
        }

        composable("loginScreen"){
            LoginScreen(navController = navController, authViewModel = authViewModel)
        }
        composable("registerScreen"){
            RegisterScreen(navController = navController, authViewModel = authViewModel)
        }
        composable("mainScreen"){
            isBottomBarVisible.value = true
            MainScreen(
                navController = navController,
                mainViewModel = mainViewModel,
                cartViewModel = cartViewModel
                )
        }
        composable("favouritesScreen"){
            isBottomBarVisible.value = true
            FavouritesScreen(favouriteViewModel = favouriteViewModel)
        }
        composable("cartScreen"){
            isBottomBarVisible.value = true
            CartScreen(cartViewModel = cartViewModel)
        }
        composable("searchScreen"){
            isBottomBarVisible.value = true
            SearchScreen(navController = navController, searchViewModel = searchViewModel)
        }
        composable("movieDetailScreen/{movie}",
            arguments = listOf(navArgument("movie") { type = NavType.StringType } )
        ){
            isBottomBarVisible.value = false
            val json = it.arguments?.getString("movie")
            val movieObject = Gson().fromJson(json, Movies::class.java)
            MovieDetailScreen(
                movie = movieObject,
                cartViewModel = cartViewModel,
                favouriteViewModel = favouriteViewModel,
                mainViewModel = mainViewModel,
                navController = navController)
        }
    }

}