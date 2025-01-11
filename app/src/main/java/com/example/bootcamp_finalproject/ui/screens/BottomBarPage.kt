package com.example.bootcamp_finalproject.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bootcamp_finalproject.R
import com.example.bootcamp_finalproject.ui.screens.components.AppDrawer
import com.example.bootcamp_finalproject.ui.screens.components.BottomBar
import com.example.bootcamp_finalproject.ui.screens.navigation.PageTransition
import com.example.bootcamp_finalproject.ui.theme.Colors
import com.example.bootcamp_finalproject.ui.viewmodels.AuthState
import com.example.bootcamp_finalproject.ui.viewmodels.AuthViewModel
import com.example.bootcamp_finalproject.ui.viewmodels.CartViewModel
import com.example.bootcamp_finalproject.ui.viewmodels.FavouriteViewModel
import com.example.bootcamp_finalproject.ui.viewmodels.MainViewModel
import com.example.bootcamp_finalproject.ui.viewmodels.SearchViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBarPage(
    navController: NavController,
    authViewModel: AuthViewModel,
    mainViewModel: MainViewModel,
    cartViewModel: CartViewModel,
    favouriteViewModel: FavouriteViewModel,
    searchViewModel: SearchViewModel
) {
    val authState = authViewModel.authState.observeAsState()
    var userEmail by remember { mutableStateOf("Guest") }
    val systemUiController = rememberSystemUiController()

    LaunchedEffect(Unit) {
        systemUiController.setSystemBarsColor(color = Colors.black)
    }

    // Update user email and navigate to authentication screen if the user is not authenticated
    LaunchedEffect(authState.value) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        userEmail = currentUser?.email ?: "Guest"
        if (authState.value is AuthState.UnAuthenticated) {
            navController.navigate("authenticationScreen")
        }
    }

    val selectedItem = remember { mutableStateOf(0) }
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val isBottomBarVisible = remember { mutableStateOf(true) }

    // ModalNavigationDrawer for the app's navigation drawer
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            AppDrawer(userEmail = userEmail) { authViewModel.signOut() }
        }
    ) {
        Scaffold(
            topBar = {
                if (isBottomBarVisible.value) {
                    TopAppBar(
                        title = {
                            Image(
                                painter = painterResource(id = R.drawable.cinemate),
                                contentDescription = "App Logo",
                                modifier = Modifier.size(290.dp)
                            )
                        },
                        navigationIcon = {
                            IconButton(onClick = { scope.launch { drawerState.open() } }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.drawer_icon),
                                    contentDescription = "Menu",
                                    modifier = Modifier.size(30.dp)
                                )
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Colors.backgroundColor,
                            titleContentColor = Colors.barTitleColor,
                            navigationIconContentColor = Colors.barTitleColor
                        )
                    )
                }
            },
            bottomBar = {
                if (isBottomBarVisible.value) {
                    BottomBar(selectedItem.value) { selectedItem.value = it }
                }
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(Colors.black)
            ) {
                PageTransition(
                    authViewModel = authViewModel,
                    mainViewModel = mainViewModel,
                    searchViewModel = searchViewModel,
                    selectedPage = when (selectedItem.value) {
                        0 -> "mainScreen"
                        1 -> "searchScreen"
                        2 -> "favouritesScreen"
                        else -> "cartScreen"
                    },
                    cartViewModel = cartViewModel,
                    isBottomBarVisible = isBottomBarVisible,
                    favouriteViewModel = favouriteViewModel
                )
            }
        }
    }
}