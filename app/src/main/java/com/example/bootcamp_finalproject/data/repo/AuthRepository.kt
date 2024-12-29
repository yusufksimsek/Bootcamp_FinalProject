package com.example.bootcamp_finalproject.data.repo

import com.example.bootcamp_finalproject.data.datasource.FirebaseAuthDataSource
import com.example.bootcamp_finalproject.data.entity.User

class AuthRepository(private val authDataSource: FirebaseAuthDataSource) {
    suspend fun login(email: String, password: String): Result<User> {
        return authDataSource.login(email, password)
    }

    suspend fun register(email: String, password: String): Result<User> {
        return authDataSource.register(email, password)
    }

    fun getCurrentUser(): User? {
        return authDataSource.getCurrentUser()
    }

    fun signOut() {
        authDataSource.signOut()
    }
}