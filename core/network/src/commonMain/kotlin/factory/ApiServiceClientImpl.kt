@file:Suppress("unused", "functionName")

package factory

import core.network.ApiServiceClient
import core.network.Header
import core.network.ToCustomException
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


    override suspend fun retrieveJsonData(url: String): Result<String> {
        val client = _createClient()
        return try {
            val response = client.get(url)
          //  ToCustomException().throwCustomExceptionIfAny(code = response.status, body = response.bodyAsText())

            val json = response.bodyAsText()
            Result.success(json)
        } catch (ex: Exception) {
            Result.failure(ToCustomException().convert(ex))
        } finally {
            client._closeConnection()
        }
    }


    override suspend fun post(url: String, body: Any): Result<String> {
        val client = _createClient()
        return try {
            val response = client.post(url) {
                contentType(ContentType.Application.Json)
                setBody(body)
            }
        //    ToCustomException().throwCustomExceptionIfAny(code = response.status, body = response.bodyAsText())

            val json = response.bodyAsText()
            Result.success(json)
        } catch (ex: Exception) {
            Result.failure(ToCustomException().convert(ex))
        } finally {
            client._closeConnection()
        }
    }


    override suspend fun postOrThrow(url: String, body: Any): String {
        val client = _createClient()
        try {
            val response = client.post(url) {
                contentType(ContentType.Application.Json)
                setBody(body)
            }
            //TODO:Fix it later
          //  ToCustomException().throwCustomExceptionIfAny(code = response.status, body = response.bodyAsText())
            val json = response.bodyAsText()

            return json

        } catch (ex: Exception) {
            throw ToCustomException().convert(ex)
        } finally {
            client._closeConnection()
        }
    }

    override suspend fun retrieveJsonOrThrow(url: String, header: Header): String {
        val client = _createClient()
        return try {
            val response = client.get(url) {
                header(key = header.key, value = header.value)
            }
            ToCustomException().throwCustomExceptionIfAny(code = response.status, body = response.bodyAsText())
            val json = response.bodyAsText()
            json
        } catch (ex: Exception) {
            throw ToCustomException().convert(ex)
        } finally {
            client._closeConnection()
        }
    }


    //TODO: Helper method
    private fun HttpClient._closeConnection() {
        try {
            this.close()
        } catch (_: Exception) {
        }
    }

    /**
     *  Should not cache HttpClient of CIO in client variable and reuse that is why make sure create
     *  a new HttpClient for each request to avoid [kotlinx.coroutines.JobCancellationException] or other exceptions
     */
    private fun _createClient() = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true // This will ignore unknown keys in the response
            })
        }
    }
}
