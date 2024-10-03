package core.network

import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.http.HttpStatusCode
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.TimeoutCancellationException

/**
 * The ExceptionHandler class is responsible for handling various types of exceptions
 * that may occur during HTTP requests and providing user-friendly error messages.
 *
 * - This class wraps common HTTP and network-related exceptions into a more readable format
 *   by separating the message (a brief, user-friendly description) and the cause (a detailed
 *   explanation, such as the HTTP status code or reason for failure).
 *
 * - This separation allows developers to easily understand the nature of the error,
 *   while also retaining the original cause for debugging purposes.
 *
 * - The following exceptions are handled:
 *      - RedirectResponseException: Handles 3xx redirection errors.
 *      - ClientRequestException: Handles 4xx client-side errors.
 *      - ServerResponseException: Handles 5xx server-side errors.
 *      - TimeoutCancellationException: Handles cases where the request times out.
 *      - ConnectTimeoutException: Handles connection timeout errors (e.g., server unreachable).
 *      - IOException: Handles generic network issues such as no internet connection or I/O failures.
 *      - A fallback for unexpected exceptions, with the message and cause captured appropriately.
 *
 * Example usage:
 *
 * ```kotlin
 * catch (ex: Exception) {
 *     val exceptionHandler = ExceptionHandler()
 *     Result.failure(exceptionHandler.handleException(ex))
 * }
 * ```
 *
 * This class ensures that user-facing error messages are clear and concise, while providing
 * developers with the necessary details to debug any issues effectively.
 */

internal class ToCustomException {
    fun convert(e: Exception): NetworkException {
        val causeMessage = e.cause?.toString() ?: "No cause available"
        val stackTrace = e.stackTraceToString()

        return when (e) {
            is RedirectResponseException -> NetworkException.RedirectionException(
                message = "Redirection error occurred.",
                debugMessage = "Cause: $causeMessage\nStack Trace:\n$stackTrace"
            )

            is ClientRequestException -> NetworkException.ClientErrorException(
                message = "Client error occurred.",
                debugMessage = "Cause: $causeMessage\nStack Trace:\n$stackTrace"
            )

            is ServerResponseException -> NetworkException.ServerErrorException(
                message = "Server error occurred.",
                debugMessage = "Cause: $causeMessage\nStack Trace:\n$stackTrace"
            )

            is TimeoutCancellationException -> NetworkException.RequestTimeoutException(
                message = "Request timeout. Please check your connection.",
                debugMessage = "Cause: $causeMessage\nStack Trace:\n$stackTrace"
            )

            is ConnectTimeoutException -> NetworkException.ConnectionTimeoutException(
                message = "Connection timeout. Unable to reach the server.",
                debugMessage = "Cause: $causeMessage\nStack Trace:\n$stackTrace"
            )

            is IOException -> NetworkException.NetworkIOException(
                message = "Network error. Please check your internet connection.",
                debugMessage = "Cause: $causeMessage\nStack Trace:\n$stackTrace"
            )

            else -> NetworkException.UnknownNetworkException(
                message = "Unknown NetworkException,contact with developer",
                debugMessage = "Cause: $causeMessage\nStack Trace:\n$stackTrace"
            )
        }

    }

    fun throwCustomExceptionIfAny(code: HttpStatusCode,body: String?=null) {
        when (code) {
            HttpStatusCode.Unauthorized -> {
                throw NetworkException.UnauthorizedException(
                    message = "Session expired. Please sign in again.",
                    debugMessage = "Unauthorized access (401),server response is\n$body"
                )
            }

            else -> {

            }
        }
    }

}
