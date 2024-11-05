@file:Suppress("unused","spellCheckingInspection")

package core.customexception

/**
 * [CustomException] is open so that `domain` layer can add it own exception(if needed) that is inherited from this exception
 * @param debugMessage the message for developer to find the causes or source of error for easy debugging
 * @param message  this will directly shown to end user so make it short enough and user friendly
 * @param code will help to identify which exception occurred and can show in UI since this intended to be short
 **/
sealed  class CustomException(
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

    class ServerFeedbackExecpton(message: String, debugMessage: String="Server send a feedback instead of exepected response") : CustomException(
        message = message,
        debugMessage = debugMessage
    )
    /**
     * - Thrown when a database connection fails.
     * - This could be due to network issues, server unavailability, or misconfiguration.
     */
    class ConnectionFailedException(exception: Throwable) : CustomException(
        message = "Failed to connect to the database",
        debugMessage = "Database connection failure:\nMessage: ${exception.message}\nCause: ${exception.cause}"
    )

    /**
     * - Thrown when the queried data is not found in the database.
     * - This usually occurs when a specific document, record, or entity is expected but does not exist.
     */
    class DataNotFoundException(
        override val message: String = "Requested data not found",
        debugMessage: String
    ) : CustomException(
        message=message,
        debugMessage = debugMessage
    )


    /**
     * - Thrown when there is a conflict with existing data in the database.
     * - Typically, this happens when trying to insert or update a record that violates
     *   unique constraints (e.g., duplicate keys, existing entries with the same values).
     */
    class DataConflictException(entity: String) : CustomException(
        message = "Data conflict occurred",
        debugMessage = "Conflict with existing data for entity: $entity"
    )

    /**
     * - Thrown when the format or structure of the query is invalid or unrecognized by the database.
     * - This can occur due to syntax errors or unsupported query parameters.
     */
    class InvalidQueryException(query: String) : CustomException(
        message = "Invalid database query",
        debugMessage = "Query syntax error or unsupported query structure:\n$query"
    )



    /**
     * - Thrown when there is an unauthorized access attempt to the database, such as invalid credentials or missing permissions.
     * - This usually occurs when the client does not have the proper rights to perform certain operations.
     */
    class UnauthorizedAccessException : CustomException(
        message = "Unauthorized access to the database",
        debugMessage = "Attempted access without proper authentication or authorization"
    )

    /**
     * - Thrown when an unknown or unexpected error occurs while interacting with the database.
     * - This acts as a catch-all for errors that do not fall under the other exceptions.
     */
    class UnknownCustomException(exception: Throwable) : CustomException(
        message = "An unexpected error occurred",
        debugMessage = "${exception.printStackTrace()}"
    )

    /**
     * - Thrown when an attempt to insert data fails because the record already exists in the database.
     * - This typically occurs when trying to insert a new record that conflicts with an existing one, such as
     *   attempting to insert a record with a unique key that is already present.
     */
    class DataAlreadyExistsException(entity: String) : CustomException(
        message = "Exists already, Consider updating.",
        debugMessage = "Attempt to insert an existing record for entity: $entity"
    )
}
