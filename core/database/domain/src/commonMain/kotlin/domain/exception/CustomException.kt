package domain.exception

/**
 * Common exceptions for databases such as MongoDB, Firebase, or any other server,
 * meant to handle errors in a framework-independent manner.
 *
 * These exceptions are designed to be used for various data sources, handling
 * common issues such as connection problems, data conflicts, or invalid queries.
 *
 * @param debugMessage the message for developer to find the causes or source of error for easy debugging
 * @param message this will directly be shown to the end user, so it should be user-friendly
 **/

sealed class CustomException(override val message: String, val debugMessage: String) :
    Throwable(message = message, cause = Throwable(debugMessage)) {

    override fun toString(): String {
        return "message:$message\nDebug Message:$debugMessage"
    }

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
     * - Thrown when an attempt to parse the database's response JSON has failed.
     * - This indicates that the response format is unrecognized or doesn't match the expected schema/entity.
     */
    class JsonParseException(json: String) : CustomException(
        message = "Unrecognized response from the database",
        debugMessage = "The database returned a response in an unexpected JSON format:\n$json"
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
