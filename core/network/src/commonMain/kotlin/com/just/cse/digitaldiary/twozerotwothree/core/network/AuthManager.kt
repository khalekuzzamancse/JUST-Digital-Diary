package com.just.cse.digitaldiary.twozerotwothree.core.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json

object AuthManager {
    private const val loginUrl = "https://diary.rnzgoldenventure.com/api/login"
    private const val registerUrl = "https://diary.rnzgoldenventure.com/api/register"
    private const val TAG = "NetworkModule:AuthManager "
    private fun log(message: String) {
        println("$TAG$message")
    }

    var authToken: String? = null
        private set

    suspend fun login(username: String, password: String): Boolean {
        val body = LoginData(
            usernameOrEmail = username,
            password = password
        )
        val httpClient = HttpClient {
            install(ContentNegotiation) {
                json()
            }
        }

        return try {
            val response: LoginResponse = httpClient.post(loginUrl) {
                contentType(ContentType.Application.Json)
                setBody(body)
            }.body()
            if (response.message == "Success") {
                authToken = response.token
                log("Login successful with token: $authToken")
                true
            } else
                false
        } catch (_: Exception) {
            false
        }
    }

    suspend fun register(
        name: String,
        email: String,
        username: String,
        password: String
    ): Boolean {
        val body = RegisterData(
            name = name,
            email = email,
            username = username,
            password = password,
        )
        val httpClient = HttpClient {
            install(ContentNegotiation) {
                json()
            }
        }

        return try {
            val response: RegistrationResponse = httpClient.post(registerUrl) {
                contentType(ContentType.Application.Json)
                setBody(body)
            }.body()
            if (response.message == "Registration Success") {
                log("Register successful: $response")
                true
            } else
                false
        } catch (_: Exception) {
            false
        }
    }
}