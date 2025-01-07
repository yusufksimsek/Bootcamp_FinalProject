package com.example.bootcamp_finalproject.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
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

    LaunchedEffect(authState.value) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        userEmail = currentUser?.email ?: "Guest"
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
            ModalDrawerSheet(
                drawerContainerColor = Colors.backgroundColor
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    // Kullanıcı profili (görsel ve isim)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp ,top = 30.dp, bottom = 30.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.user_icon),
                            contentDescription = "Profile Image",
                            tint = Colors.drawerIconColor,
                            modifier = Modifier
                                .size(40.dp)
                        )
                        Spacer(modifier = Modifier.width(20.dp))
                        Text(
                            text = userEmail, // Firebase'den kullanıcı adını al
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Colors.drawerItemColor
                        )
                    }
                    // Menü öğeleri ve ilgili ikonları
                    val menuItems = listOf(
                        "Edit Profile" to R.drawable.edit_profile_icon,
                        "Settings" to R.drawable.settings_icon,
                        "Change Password" to R.drawable.password_icon,
                        "Notification" to R.drawable.notification_icon,
                        "Download" to R.drawable.download_icon,
                        "Security" to R.drawable.security_icon,
                        "Help Center" to R.drawable.help_icon,
                        "Logout" to R.drawable.logout_icon
                    )
                    menuItems.forEachIndexed { index, item ->
                        Column(modifier = Modifier.padding(6.dp)) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        if (item.first == "Logout") {
                                            // Çıkış işlemi
                                            authViewModel.signOut()
                                        }
                                    }
                                    .padding(vertical = 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                // Sol tarafta ikon
                                Icon(
                                    painter = painterResource(id = item.second),
                                    contentDescription = "${item.first} Icon",
                                    tint = if (item.first == "Logout") Color.Red else Colors.drawerIconColor, // Logout için kırmızı
                                    modifier = Modifier
                                        .size(30.dp)
                                        .padding(end = 10.dp) // İkon ile metin arasında boşluk
                                )

                                // Menü metni
                                Text(
                                    text = item.first,
                                    fontSize = 16.sp,
                                    color = if (item.first == "Logout") Color.Red else Colors.drawerItemColor, // Logout için kırmızı
                                    modifier = Modifier.weight(1f)
                                )

                                // Sağ tarafta ok ikonu
                                Icon(
                                    modifier = Modifier.size(20.dp),
                                    painter = painterResource(id = R.drawable.arrow_right),
                                    contentDescription = "Arrow Icon",
                                    tint = Colors.drawerIconColor
                                )
                            }
                            if (index != menuItems.size - 1) { // Son öğe için divider eklemiyoruz
                                Divider(color = Colors.dividerColor, thickness = 1.dp)
                            }
                        }
                    }
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                if (isBottomBarVisible.value) {
                    TopAppBar(
                        title = {
                            Text(
                                "Movies App",
                                fontSize = 25.sp,
                                color = Colors.barTitleColor
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
                    BottomAppBar(
                        containerColor = Colors.backgroundColor,
                        contentColor = Colors.barTitleColor,
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
                                        painter = painterResource(id = R.drawable.favourite),
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
                                        painter = painterResource(id = R.drawable.shop),
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
                    .background(Colors.black)
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