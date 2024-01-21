package com.just.cse.digitaldiary.twozerotwothree.core.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.post
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable

const val loginUrl="https://diary.rnzgoldenventure.com/api/login"
@Serializable
data class LoginData(
    val usernameOrEmail: String = "tests2",
    val password: String = "test@123"
)

suspend fun apiDemo() {
    sendLoginRequest()
}
    suspend fun sendLoginRequest() {
        val httpClient = HttpClient {
            install(ContentNegotiation) {
                json()
            }
        }

        try {
            val response: LoginResponse = httpClient.post(loginUrl) {
                contentType(ContentType.Application.Json)
                setBody(LoginData())
            }.body()
            println("API Fetched Result: $response")
        } catch (e: Exception) {
            println("Error making the request: ${e.message}")
        }
    }



suspend fun getRequest() {
    val httpClient = HttpClient {
        install(ContentNegotiation) {
            json()
        }
    }
    val res = httpClient.request {
        method = HttpMethod.Get
        url("https://diary.rnzgoldenventure.com/api/faculties")
       // header("Authorization", authToken)
    }.body<FacultiesResponse>()
    println(res)
}

@Serializable
data class LoginResponse(
    val message: String,
    val token: String
)


@Serializable
data class Faculty(
    val id: Int,
    val faculty_id: String,
    val name: String,
    val departmentsCount: Int
)

@Serializable
data class FacultiesResponse(
    val message: String,
    val data: List<Faculty>
)
