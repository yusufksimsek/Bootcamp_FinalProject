package com.example.bootcamp_finalproject.ui.screens.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.bootcamp_finalproject.R
import com.example.bootcamp_finalproject.ui.theme.Colors

@Composable
fun BottomBar(
    selectedItem: Int,
    onItemSelected: (Int) -> Unit
) {
    BottomAppBar(
        containerColor = Colors.backgroundColor,
        contentColor = Colors.barTitleColor,
        content = {
            val items = listOf(
                "Home" to R.drawable.home,
                "Search" to R.drawable.search,
                "Favourites" to R.drawable.favourite,
                "Cart" to R.drawable.shop
            )
            items.forEachIndexed { index, item ->
                NavigationBarItem(
                    selected = selectedItem == index,
                    onClick = { onItemSelected(index) },
                    label = { Text(text = item.first) },
                    icon = {
                        Icon(
                            painter = painterResource(id = item.second),
                            contentDescription = null,
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
        }
    )
}