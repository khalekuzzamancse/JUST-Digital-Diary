package com.just.cse.digitaldiary.twozerotwothree.core.network.ktor_clinet.get

import com.just.cse.digitaldiary.twozerotwothree.core.network.ktor_clinet.post.NetworkResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json

suspend inline fun <reified T> getRequest(url: String, header: Header): NetworkResponse<T> {
    val httpClient = HttpClient {
        install(ContentNegotiation) {
            json()
        }
    }
    return try {
        val response = httpClient.get(url){
            header(key=header.key, value=header.value)
        }.body<T>()
        NetworkResponse(result = response, reason = null, isSuccess = true)
    } catch (ex: Exception) {
        NetworkResponse(result = null, reason = "Exception Occurs", isSuccess = false)
    }
}
suspend inline fun <reified T> getRequest2(url: String, header: Header): Result<T> {
    val httpClient = HttpClient {
        install(ContentNegotiation) {
            json()
        }
    }
    return try {
        val response = httpClient.get(url){
            header(key=header.key, value=header.value)
        }.body<T>()
        Result.success(response)
    } catch (ex: Exception) {
        Result.failure(ex)
    }
}

