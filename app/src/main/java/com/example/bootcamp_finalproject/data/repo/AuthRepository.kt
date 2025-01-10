package com.example.bootcamp_finalproject.data.repo

import com.example.bootcamp_finalproject.data.datasource.AuthDataSource
import com.example.bootcamp_finalproject.data.entity.user.User

class AuthRepository(private val authDataSource: AuthDataSource) {
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