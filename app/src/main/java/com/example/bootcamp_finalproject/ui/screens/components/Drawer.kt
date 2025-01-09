package com.example.bootcamp_finalproject.ui.screens.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Icon
import com.example.bootcamp_finalproject.R
import com.example.bootcamp_finalproject.ui.theme.Colors

@Composable
fun AppDrawer(
    userEmail: String,
    onLogoutClick: () -> Unit
) {
    ModalDrawerSheet(
        drawerContainerColor = Colors.backgroundColor
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            // Kullanıcı profili
            DrawerHeader(userEmail)
            // Menü öğeleri
            DrawerMenu(onLogoutClick)
        }
    }
}

@Composable
fun DrawerHeader(userEmail: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, top = 30.dp, bottom = 30.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.user_icon),
            contentDescription = "Profile Image",
            tint = Colors.drawerIconColor,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(20.dp))
        Text(
            text = userEmail,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Colors.drawerItemColor
        )
    }
}

@Composable
fun DrawerMenu(onLogoutClick: () -> Unit) {
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
                            onLogoutClick()
                        }
                    }
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Sol tarafta ikon
                Icon(
                    painter = painterResource(id = item.second),
                    contentDescription = "${item.first} Icon",
                    tint = if (item.first == "Logout") Color.Red else Colors.drawerIconColor,
                    modifier = Modifier
                        .size(30.dp)
                        .padding(end = 10.dp)
                )

                // Menü metni
                Text(
                    text = item.first,
                    fontSize = 16.sp,
                    color = if (item.first == "Logout") Color.Red else Colors.drawerItemColor,
                    modifier = Modifier.weight(1f)
                )

                // Sağ tarafta ok ikonu
                Icon(
                    painter = painterResource(id = R.drawable.arrow_right),
                    contentDescription = "Arrow Icon",
                    tint = Colors.drawerIconColor,
                    modifier = Modifier.size(20.dp)
                )
            }
            if (index != menuItems.size - 1) {
                Divider(color = Colors.dividerColor, thickness = 1.dp)
            }
        }
    }
}
