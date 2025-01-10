package com.example.bootcamp_finalproject.ui.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.room.Query
import androidx.room.util.query
import com.example.bootcamp_finalproject.R
import com.example.bootcamp_finalproject.ui.theme.Colors

@Composable
fun SearchBar(
    query: String,
    onSearch: (String) -> Unit
) {
    var text by remember {
        mutableStateOf(query)
    }

    Box(modifier = Modifier.padding(10.dp)
    ) {
        TextField(
            value = text,
            onValueChange = {
                text = it
                onSearch(it)
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Colors.barTitleColor),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Colors.searchCartBackgroundColor,
                unfocusedContainerColor = Colors.searchCartBackgroundColor,
                focusedIndicatorColor = Colors.searchCartBackgroundColor, // Çizgiyi kaldırıyoruz
                unfocusedIndicatorColor = Colors.searchCartBackgroundColor // Çizgiyi kaldırıyoruz
            ),
            trailingIcon = {
                IconButton(onClick = {
                    onSearch(text)
                }) {
                    Icon(
                        modifier = Modifier.size(20.dp),
                        painter = painterResource(id = R.drawable.search),
                        contentDescription = "Search Icon",
                        tint = Colors.barTitleColor
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Colors.searchCartBackgroundColor)
                .padding(horizontal = 10.dp)
        )
    }
}