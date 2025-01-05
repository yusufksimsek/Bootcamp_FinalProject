package com.example.bootcamp_finalproject.ui.screens.authorization.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.bootcamp_finalproject.ui.theme.Colors

@Composable
fun EmailField(value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 40.dp),
        value = value,
        onValueChange = onValueChange,
            label = { Text(text = "Email") },
        textStyle = TextStyle(color = Colors.loginRegisterColor)
    )

}