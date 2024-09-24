@file:Suppress("UnUsed")

package auth.domain.exception

import common.docs.domain_layer.CustomExceptionDoc

/**
 * Further discussion on:
 *   - `Repository`: see [CustomExceptionDoc]
 * @param debugMessage the message for developer to find the causes or source of error
 * @param message  This represents the message that can be converted to a UI-friendly, short format
 **/

sealed class CustomException(override val message: String, val debugMessage: String) :
    Throwable(message = message, cause = Throwable(debugMessage)) {
    override fun toString(): String {
        return "message:$message\nDebug Message:$debugMessage"
    }

    /**
     *- This  Exception should thrown when all attempts to parse the server's JSON response have failed.
     * - This indicates that the response format is unrecognized or doesn't match any expected schema/entity
     */
    class JsonParseException(json: String) :
        CustomException(
            message = "Unrecognized JSON response from the server",
            debugMessage = "The server returned a response in an unexpected format:\n$json"
        )


    /**
     * Exception thrown when a server connection issue occurs (e.g., unable to connect, server down, or network failure).
     * This exception is propagated from `core.network` and already contains meaningful information about the failure.
     * No additional handling is needed beyond passing it up the call chain.
     */
    class ServerConnectingException(exception: Throwable) : CustomException(
        message = exception.message ?: "Unknown server connection issue",
        debugMessage = "Server connection problem:\nMessage: ${exception.message}\nCause: ${exception.cause}"
    )

    /**
     * Exception thrown for any unknown or unexpected  errors.
     *
     * @param debugMessage A detailed message describing the unexpected error.
     */
    class UnKnownException(message: String, debugMessage: String) : CustomException(
        message = message,
        debugMessage = debugMessage
    )

    /**
     * - This Exception should thrown when the server responds with an error message instead of the expected schema or entity
     * - This typically occurs when the server returns an error response in JSON format, indicating a failure in processing
     * the request
     */
    class ErrorFromServer(error: String) : CustomException(
        message = error,
        debugMessage = "Server returned an error response: $error"
    )

}
