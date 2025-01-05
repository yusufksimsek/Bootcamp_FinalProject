package com.example.bootcamp_finalproject.ui.screens.authorization.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.bootcamp_finalproject.R

@Composable
fun PasswordField(
    value: String,
    onValueChange: (String) -> Unit
) {
    var isPasswordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = "Password") },
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        modifier = Modifier.fillMaxWidth().padding(horizontal = 40.dp),
        trailingIcon = {
            IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                val visibilityIcon = if (isPasswordVisible) R.drawable.visibility_icon else R.drawable.visibility_off_icon
                Icon(
                    painter = painterResource(id = visibilityIcon),
                    contentDescription = if (isPasswordVisible) "Hide password" else "Show password"
                )
            }
        }
    )
}