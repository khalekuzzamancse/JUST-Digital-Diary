package com.just.cse.digitaldiary.twozerotwothree.data.repository.auth_repository.data

import com.just.cse.digitaldiary.twozerotwothree.core.network.AuthManager

object AuthenticationManager {
    suspend fun login(username: String, password: String): Boolean =
        AuthManager.login(username, password)

    suspend fun register(
        name: String,
        email: String,
        username: String,
        password: String
    ): Boolean =
        AuthManager.register(name = name, email = email, username = username, password = password)
}