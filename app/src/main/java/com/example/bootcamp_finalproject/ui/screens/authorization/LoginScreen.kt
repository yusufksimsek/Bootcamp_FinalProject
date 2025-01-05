package com.example.bootcamp_finalproject.ui.screens.authorization

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bootcamp_finalproject.ui.theme.Colors
import com.example.bootcamp_finalproject.ui.viewmodels.AuthState
import com.example.bootcamp_finalproject.ui.viewmodels.AuthViewModel

@Composable
fun LoginScreen(navController: NavController,authViewModel: AuthViewModel) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val authState = authViewModel.authState.observeAsState()
    val context = LocalContext.current

    LaunchedEffect(authState.value) {
        when(authState.value){
            is AuthState.Authenticated -> navController.navigate("bottomBarPage")
            is AuthState.Error -> Toast.makeText(
                context,
                (authState.value as AuthState.Error).message,
                Toast.LENGTH_SHORT
            ).show()
            else -> Unit
        }
    }

    Column(modifier = Modifier.fillMaxSize()
                              .background(Colors.backgroundColor),
           verticalArrangement = Arrangement.Center,
           horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Login",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Colors.loginRegisterColor
        )
        
        Spacer(modifier = Modifier.height(25.dp))

        OutlinedTextField(value = email, onValueChange = {
            email = it
        }, label = { Text(text = "Email") } )

        OutlinedTextField(value = password, onValueChange = {
            password = it
        }, label = { Text(text = "Password") } )

        Spacer(modifier = Modifier.height(25.dp))

        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Colors.buttonColor,
                contentColor = Colors.black,
            ),
            onClick = {
            authViewModel.login(email, password)
        }) {
            Text(
                text = "Login",
                color = Colors.black
                )
        }
        
        Spacer(modifier = Modifier.height(12.dp))

        TextButton(onClick = {
            navController.navigate("registerScreen")
        }) {
            Text(
                text = "Don't have an account? Register",
                color = Colors.haveAccountColor,

            )
        }
    }

}