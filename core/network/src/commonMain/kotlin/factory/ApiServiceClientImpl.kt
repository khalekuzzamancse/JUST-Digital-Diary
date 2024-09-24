@file:Suppress("unused","functionName")

package factory

import component.ApiServiceClient
import component.ExceptionToError
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

/**- Outer module should not create direct instance of this but can use it by getting
 * instance via `factory method`
 */
class ApiServiceClientImpl internal constructor() : ApiServiceClient {
    private val tag: String = this.javaClass.simpleName
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true // This will ignore unknown keys in the response
            })
        }
    }


    override suspend fun retrieveJsonData(url: String): Result<String> {
        return try {
            val httpResponse = client.get(url)
            val json = httpResponse.bodyAsText()
            Result.success(json)
        } catch (ex: Exception) {
            Result.failure(ExceptionToError().convert(ex))
        } finally {
            _closeConnection()
        }
    }


    override suspend fun post(url: String, body: Any): Result<String> {
        return try {
            val json = client.post(url) {
                contentType(ContentType.Application.Json)
                setBody(body)
            }.bodyAsText()
            Result.success(json)
        } catch (ex: Exception) {
            Result.failure(ExceptionToError().convert(ex))
        }
        finally {
            _closeConnection()
        }
    }
    private fun _closeConnection() {
        try {
            client.close()
        } catch (_: Exception) {
        }
    }
}
