package com.just.cse.digital_diary.two_zero_two_three.common_ui

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.header
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
suspend fun apiDemo() {
    sendJsonPost()
}
suspend fun getJsonPost(){
    val httpClient = HttpClient() {
        install(ContentNegotiation) {
            json()
        }

    }
    val res = httpClient.request {
        method = HttpMethod.Get
        url("https://api.thecatapi.com/v1/images/search")
    }
    println(res)

}
suspend fun sendJsonPost(){
    val httpClient = HttpClient() {
        install(ContentNegotiation) {
            json()
        }

    }
    val response = httpClient.post(loginUrl) {
        contentType(ContentType.Application.Json)
        setBody(LoginData())
    }
    println(response)

}


const val authToken =
    "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOiIxOTExMDEuY3NlOGFlNDdkYTdkY2VkIiwicm9sZV9pZCI6MTMsImlhdCI6MTcwNTcyMjQ3NSwiZXhwIjoxNzA1ODk1Mjc1fQ.9cm3lnER0-heA4-9j7shcIsmdBQabryjL8QHVmqOejo"

suspend fun getRequest() {
    val httpClient = HttpClient() {
        install(ContentNegotiation) {
            json()
        }
    }
    val res = httpClient.request {
        method = HttpMethod.Get
        url("https://diary.rnzgoldenventure.com/api/faculties")
        header("Authorization", authToken)
    }.body<Response2>()
    println(res)
}

@Serializable
data class User(
    val name: String="Md Khalek",
    val email: String="124",
    val gender:String="123",
    val status:String="sss"
)
@Serializable
data class LoginResponse(
    val message: String,
    val token: String
)

@Serializable
data class LoginData(
    val usernameOrEmail: String = "tests2",
    val password: String = "test@123"
)

@Serializable
data class Faculty(
    val id: Int,
    val faculty_id: String,
    val name: String,
    val departmentsCount: Int
)

@Serializable
data class Response2(
    val message: String,
    val data: List<Faculty>
)
@Serializable
data class PostResponse(
    val body: String,
    val title: String,
    val id: Int,
    val userId: Int
)
@Serializable
data class PostRequest(
    val body: String,
    val title: String,
    val userId: Int
)