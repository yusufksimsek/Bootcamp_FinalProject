package com.example.bootcamp_finalproject.ui.screens.authorization

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bootcamp_finalproject.ui.theme.Colors
import com.example.bootcamp_finalproject.ui.viewmodels.AuthState
import com.example.bootcamp_finalproject.ui.viewmodels.AuthViewModel

@Composable
fun RegisterScreen(navController: NavController, authViewModel: AuthViewModel) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }

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

    Column(
        modifier = Modifier
        .fillMaxSize()
        .background(Colors.backgroundColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Sign Up",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = Colors.loginRegisterColor
            )

        Spacer(modifier = Modifier.height(35.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp),
            value = username,
            onValueChange = {
                username = it
            }, label = { Text(text = "Username") } )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp),
            value = email,
            onValueChange = {
            email = it
        }, label = { Text(text = "Email") } )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp),
            value = password,
            onValueChange = {
            password = it
        }, label = { Text(text = "Password") } )

        Spacer(modifier = Modifier.height(35.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
                .height(45.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Colors.buttonColor,
                contentColor = Colors.black,
            ),
            onClick = {
            authViewModel.register(email, password)
        }) {
            Text(
                text = "Create Account",
                color = Colors.black,
                fontSize = 18.sp
                )
        }

        Spacer(modifier = Modifier.height(15.dp))

        val annotatedText = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = Colors.haveAccountColor
                )
            ) {
                append("Already have an account? ")
            }

            pushStringAnnotation(tag = "login", annotation = "loginScreen")
            withStyle(
                style = SpanStyle(
                    color = Colors.mainColor,
                )
            ) {
                append("Login")
            }
            pop()
        }

        ClickableText(
            text = annotatedText,
            onClick = { offset ->
                annotatedText.getStringAnnotations(tag = "login", start = offset, end = offset)
                    .firstOrNull()?.let {
                        navController.navigate(it.item)
                    }
            }
        )
    }
}
