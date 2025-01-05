package com.example.bootcamp_finalproject.data.entity.user

import com.google.firebase.auth.FirebaseUser

data class User(
    val email: String
)

fun FirebaseUser.toUser(): User {
    return User(
        email = this.email ?: ""
    )
}