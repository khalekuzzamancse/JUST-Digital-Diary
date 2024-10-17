package core.database.network

sealed class NetworkException(override val message: String, val debugMessage: String) :
    Throwable(message = message, cause = Throwable(debugMessage)) {
    
    override fun toString(): String {
        return "message: $message\nDebug Message: $debugMessage"
    }


    // Specific network-related exceptions, each takes both a message and debugMessage parameter
    class RedirectionException(message: String, debugMessage: String) : NetworkException(
        message = message,
        debugMessage = debugMessage
    )

    class ClientErrorException(message: String, debugMessage: String) : NetworkException(
        message = message,
        debugMessage = debugMessage
    )

    class ServerErrorException(message: String, debugMessage: String) : NetworkException(
        message = message,
        debugMessage = debugMessage
    )

    class RequestTimeoutException(message: String, debugMessage: String) : NetworkException(
        message = message,
        debugMessage = debugMessage
    )

    class ConnectionTimeoutException(message: String, debugMessage: String) : NetworkException(
        message = message,
        debugMessage = debugMessage
    )

    class NetworkIOException(message: String, debugMessage: String) : NetworkException(
        message = message,
        debugMessage = debugMessage
    )

    class UnknownNetworkException(message: String, debugMessage: String) : NetworkException(
        message = message,
        debugMessage = debugMessage
    )
    /**New UnauthorizedException for HTTP 401 errors, such as for token is not valid*/
    class UnauthorizedException(message: String, debugMessage: String) : NetworkException(
        message = message,
        debugMessage = debugMessage
    )
}
