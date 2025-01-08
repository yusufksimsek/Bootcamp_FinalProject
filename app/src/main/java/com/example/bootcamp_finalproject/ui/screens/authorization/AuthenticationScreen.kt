package com.example.bootcamp_finalproject.ui.screens.authorization

import androidx.compose.foundation.background
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bootcamp_finalproject.ui.screens.authorization.components.SlidingBackgroundImage
import com.example.bootcamp_finalproject.ui.theme.Colors

@Composable
fun AuthenticationScreen(navController: NavController) {

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
                    color = Colors.black
                    )
            }
            Spacer(modifier = Modifier.height(10.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .padding(horizontal = 40.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Colors.mainColor
                ),
                onClick = {
                    navController.navigate("registerScreen")
                }
            ) {
                Text(
                    text = "Sign Up",
                    fontSize = 18.sp,
                    color = Colors.black
                )
            }
        }
    }

}