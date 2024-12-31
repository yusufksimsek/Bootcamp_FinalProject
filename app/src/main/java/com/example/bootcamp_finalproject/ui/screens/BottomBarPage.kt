package com.example.bootcamp_finalproject.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bootcamp_finalproject.R
import com.example.bootcamp_finalproject.ui.screens.navigation.PageTransition
import com.example.bootcamp_finalproject.ui.viewmodels.AuthState
import com.example.bootcamp_finalproject.ui.viewmodels.AuthViewModel
import com.example.bootcamp_finalproject.ui.viewmodels.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBarPage(
    navController: NavController,
    authViewModel: AuthViewModel,
    mainViewModel: MainViewModel) {
    val authState = authViewModel.authState.observeAsState()

    LaunchedEffect(authState.value) {
        when(authState.value){
            is AuthState.UnAuthenticated -> navController.navigate("loginScreen")
            else -> Unit
        }
    }

    val secilenItem = remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Movies App", fontSize = 25.sp) },
            )
        },
        bottomBar = {
            BottomAppBar (content = {
                NavigationBarItem(
                    selected = secilenItem.value == 0,
                    onClick = { secilenItem.value = 0 },
                    label = {Text(text = "Home")},
                    icon = { Icon(painter = painterResource(id = R.drawable.home_icon), contentDescription = "") })
                NavigationBarItem(
                    selected = secilenItem.value == 1,
                    onClick = { secilenItem.value = 1 },
                    label = {Text(text = "Search")},
                    icon = { Icon(painter = painterResource(id = R.drawable.search_icon), contentDescription = "") })
                NavigationBarItem(
                    selected = secilenItem.value == 2,
                    onClick = { secilenItem.value = 2 },
                    label = {Text(text = "Favourites")},
                    icon = { Icon(painter = painterResource(id = R.drawable.fav_icon), contentDescription = "") })
                NavigationBarItem(
                    selected = secilenItem.value == 3,
                    onClick = { secilenItem.value = 3 },
                    label = {Text(text = "Cart")},
                    icon = { Icon(painter = painterResource(id = R.drawable.shop_icon), contentDescription = "") })
            })
        }
    ) { paddingValues ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)) {
            if (secilenItem.value == 0){
                PageTransition(authViewModel = authViewModel, mainViewModel = mainViewModel ,selectedPage = "mainScreen")
            }
            if (secilenItem.value == 1){
                PageTransition(authViewModel = authViewModel, mainViewModel = mainViewModel ,selectedPage = "searchScreen")
            }
            if (secilenItem.value == 2){
                PageTransition(authViewModel = authViewModel, mainViewModel = mainViewModel ,selectedPage = "favouritesScreen")
            }
            if (secilenItem.value == 3){
                PageTransition(authViewModel = authViewModel, mainViewModel = mainViewModel ,selectedPage = "cartScreen")
            }
        }
    }

}