package com.example.bootcamp_finalproject.ui.screens.authorization

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bootcamp_finalproject.ui.screens.authorization.components.SlidingBackgroundImage
import com.example.bootcamp_finalproject.ui.theme.Colors
import com.example.bootcamp_finalproject.ui.viewmodels.AuthState
import com.example.bootcamp_finalproject.ui.viewmodels.AuthViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun AuthenticationScreen(navController: NavController, authViewModel: AuthViewModel) {

    val authState = authViewModel.authState.observeAsState()
    val systemUiController = rememberSystemUiController()
    val isLoading = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        systemUiController.setSystemBarsColor(color = Colors.black)
    }

    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthState.Loading -> isLoading.value = true
            is AuthState.Authenticated -> navController.navigate("bottomBarPage")
            else -> Unit
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Colors.black)
    ) {
        SlidingBackgroundImage()
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 60.dp)
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .padding(horizontal = 40.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Colors.mainColor
                ),
                onClick = {
                    navController.navigate("loginScreen")
                }
            ) {
                Text(
                    text = "Sign In",
                    fontSize = 18.sp,
                    color = Colors.black,
                    fontWeight = FontWeight.Bold
                    )
            }
            Spacer(modifier = Modifier.height(10.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .padding(horizontal = 40.dp)
                    .border(2.dp, Colors.mainColor,
                shape = RoundedCornerShape(50.dp)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    disabledContainerColor = Colors.mainColor
                ),
                onClick = {
                    navController.navigate("registerScreen")
                }
            ) {
                Text(
                    text = "Sign Up",
                    fontSize = 18.sp,
                    color = Colors.mainColor,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }

}