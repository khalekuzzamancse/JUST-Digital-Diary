@file:Suppress("unused","spellCheckingInspection")

package core.customexception

/**
 * [CustomException] is open so that `domain` layer can add it own exception(if needed) that is inherited from this exception
 * @param debugMessage the message for developer to find the causes or source of error for easy debugging
 * @param message  this will directly shown to end user so make it short enough and user friendly
 * @param code will help to identify which exception occurred and can show in UI since this intended to be short
 **/
open class CustomException(
    override val message: String,
    open val debugMessage: String,
    open val code: String = "",
) :
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
            message = "Unrecognized response from the server",
            debugMessage = "The server returned a response in an unexpected format:\n$json"
        ){
        override val code="JPE"
        }


    /**
     * Exception thrown when a server connection issue occurs (e.g., unable to connect, server down, or network failure).
     * This exception is propagated from `core.network` and already contains meaningful information about the failure.
     * No additional handling is needed beyond passing it up the call chain.
     */
    class ServerConnectingException(exception: Throwable) : CustomException(
        message = exception.message ?: "Unknown server connection issue",
        debugMessage = "Server connection problem:\nMessage: ${exception.message}\nCause: ${exception.cause}"
    ){
        override val code="SCE"
    }

    /**
     * Exception thrown for any unknown or unexpected  errors.
     *
     * @property debugMessage A detailed message describing the unexpected error.
     */
    class UnKnownException(exception: Throwable) : CustomException(
        message = "Something  went wrong",
        debugMessage = "${exception.printStackTrace()}"
    ){
        override val code="UNE"
    }

    /**
     * - This exception is thrown when the server responds with an error message or status,
     *   instead of the expected data or response schema.
     * - It typically occurs when the server returns an error response in a non-standard format,
     *   such as JSON containing a message like "API limit exceeded" or "Email already registered."
     * - This exception is useful for handling cases where the server indicates a failure or provides
     *   informational messages rather than structured data.
     */
    class MessageFromServer(message: String) : CustomException(
        message = message,
        debugMessage = "Server returned an message instead of expected response: $message"
    ){
        override val code="MFSIOR"//Message from server instead of response
    }

    /**
     * - This is related to when network engine has thrown an exception either connected with server or other
     */
    class NetworkIOException(message: String, debugMessage: String) : CustomException(
        message = message,
        debugMessage = debugMessage
    )
}
