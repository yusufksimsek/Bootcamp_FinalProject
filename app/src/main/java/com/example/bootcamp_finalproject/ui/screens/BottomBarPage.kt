package com.example.bootcamp_finalproject.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bootcamp_finalproject.R
import com.example.bootcamp_finalproject.ui.screens.navigation.PageTransition
import com.example.bootcamp_finalproject.ui.theme.Colors
import com.example.bootcamp_finalproject.ui.viewmodels.AuthState
import com.example.bootcamp_finalproject.ui.viewmodels.AuthViewModel
import com.example.bootcamp_finalproject.ui.viewmodels.CartViewModel
import com.example.bootcamp_finalproject.ui.viewmodels.FavouriteViewModel
import com.example.bootcamp_finalproject.ui.viewmodels.MainViewModel
import com.example.bootcamp_finalproject.ui.viewmodels.SearchViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBarPage(
    navController: NavController,
    authViewModel: AuthViewModel,
    mainViewModel: MainViewModel,
    cartViewModel: CartViewModel,
    favouriteViewModel: FavouriteViewModel,
    searchViewModel: SearchViewModel) {
    val authState = authViewModel.authState.observeAsState()

    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthState.UnAuthenticated -> navController.navigate("loginScreen")
            else -> Unit
        }
    }

    val secilenItem = remember { mutableStateOf(0) }
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val isBottomBarVisible = remember { mutableStateOf(true) }

    ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.person_icon),
                                contentDescription = "Profile Image",
                                modifier = Modifier
                                    .align(Alignment.TopCenter)
                                    .size(160.dp)
                            )
                        }
                        Text(
                            text = "Settings",
                            modifier = Modifier
                                .padding(vertical = 8.dp),
                            fontSize = 18.sp
                        )
                        TextButton(onClick = { authViewModel.signOut() }) {
                            Text(
                                text = "Logout",
                                modifier = Modifier
                                    .padding(vertical = 8.dp),
                                fontSize = 18.sp
                            )
                        }
                    }
                }
            },
        ) {
            Scaffold(
                topBar = {
                    if (isBottomBarVisible.value) {
                        TopAppBar(
                            title = { Text("Movies App", fontSize = 25.sp) },
                            navigationIcon = {
                                IconButton(onClick = { scope.launch { drawerState.open() } }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.menu_icon),
                                        contentDescription = "Menu"
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
                        BottomAppBar(
                            containerColor = Colors.backgroundColor,
                            contentColor = Colors.barTitleColor ,
                            content = {
                            NavigationBarItem(
                                selected = secilenItem.value == 0,
                                onClick = { secilenItem.value = 0 },
                                label = { Text(text = "Home") },
                                icon = {
                                    Icon(
                                        painter = painterResource(id = R.drawable.home),
                                        contentDescription = "",
                                        modifier = Modifier.size(25.dp)
                                    )
                                },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = Colors.mainColor,
                                    unselectedIconColor = Colors.barIconColor,
                                    selectedTextColor = Colors.mainColor,
                                    unselectedTextColor = Colors.barIconColor
                                )
                            )
                            NavigationBarItem(
                                selected = secilenItem.value == 1,
                                onClick = { secilenItem.value = 1 },
                                label = { Text(text = "Search") },
                                icon = {
                                    Icon(
                                        painter = painterResource(id = R.drawable.search),
                                        contentDescription = "",
                                        modifier = Modifier.size(25.dp)
                                    )
                                },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = Colors.mainColor,
                                    unselectedIconColor = Colors.barIconColor,
                                    selectedTextColor = Colors.mainColor,
                                    unselectedTextColor = Colors.barIconColor
                                )
                                )
                            NavigationBarItem(
                                selected = secilenItem.value == 2,
                                onClick = { secilenItem.value = 2 },
                                label = { Text(text = "Favourites") },
                                icon = {
                                    Icon(
                                        painter = painterResource(id = R.drawable.fav_icon),
                                        contentDescription = "",
                                        modifier = Modifier.size(25.dp)
                                    )
                                },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = Colors.mainColor,
                                    unselectedIconColor = Colors.barIconColor,
                                    selectedTextColor = Colors.mainColor,
                                    unselectedTextColor = Colors.barIconColor
                                )
                                )
                            NavigationBarItem(
                                selected = secilenItem.value == 3,
                                onClick = { secilenItem.value = 3 },
                                label = { Text(text = "Cart") },
                                icon = {
                                    Icon(
                                        painter = painterResource(id = R.drawable.shop_icon),
                                        contentDescription = "",
                                        modifier = Modifier.size(25.dp)
                                    )
                                },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = Colors.mainColor,
                                    unselectedIconColor = Colors.barIconColor,
                                    selectedTextColor = Colors.mainColor,
                                    unselectedTextColor = Colors.barIconColor
                                )
                                )
                        }
                        )
                    }
                },
            ) { paddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    if (secilenItem.value == 0) {
                        PageTransition(
                            authViewModel = authViewModel,
                            mainViewModel = mainViewModel,
                            searchViewModel = searchViewModel,
                            selectedPage = "mainScreen",
                            cartViewModel = cartViewModel,
                            isBottomBarVisible = isBottomBarVisible,
                            favouriteViewModel = favouriteViewModel
                        )
                    }
                    if (secilenItem.value == 1) {
                        PageTransition(
                            authViewModel = authViewModel,
                            mainViewModel = mainViewModel,
                            searchViewModel = searchViewModel,
                            selectedPage = "searchScreen",
                            cartViewModel = cartViewModel,
                            isBottomBarVisible = isBottomBarVisible,
                            favouriteViewModel = favouriteViewModel
                        )
                    }
                    if (secilenItem.value == 2) {
                        PageTransition(
                            authViewModel = authViewModel,
                            mainViewModel = mainViewModel,
                            searchViewModel = searchViewModel,
                            selectedPage = "favouritesScreen",
                            cartViewModel = cartViewModel,
                            isBottomBarVisible = isBottomBarVisible,
                            favouriteViewModel = favouriteViewModel
                        )
                    }
                    if (secilenItem.value == 3) {
                        PageTransition(
                            authViewModel = authViewModel,
                            mainViewModel = mainViewModel,
                            searchViewModel = searchViewModel,
                            selectedPage = "cartScreen",
                            cartViewModel = cartViewModel,
                            isBottomBarVisible = isBottomBarVisible,
                            favouriteViewModel = favouriteViewModel
                        )
                    }
                }
            }
        }
}