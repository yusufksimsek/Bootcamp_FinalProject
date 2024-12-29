package com.example.bootcamp_finalproject.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.bootcamp_finalproject.ui.viewmodels.AuthViewModel

@Composable
fun PersonScreen(authViewModel: AuthViewModel) {
    Column {
        Text(text = "Person")
        Button(onClick = { authViewModel.signOut() }) {
            Text(text = "Sign out")
        }
    }
}