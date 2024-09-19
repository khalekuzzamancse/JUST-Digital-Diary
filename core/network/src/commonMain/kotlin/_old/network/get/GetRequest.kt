package _old.network.get

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json

suspend inline fun <reified T> getRequest(url: String, header: Header): Result<T> {

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

