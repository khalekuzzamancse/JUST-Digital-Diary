@file:Suppress("UnUsed")
package schedule.domain.exception

import common.docs.domain_layer.CustomExceptionDoc


/**
 * Further discussion on:
 *   - `Repository`: see [CustomExceptionDoc]
 * @param debugMessage the message for developer to find the causes or source of error
 * @param message  This represents the message that can be converted to a UI-friendly, short format
 **/
sealed class NetworkException(message: String, debugMessage: String) :
    Throwable(message = message, cause = Throwable(debugMessage))
/**
 * Exception thrown when the system cannot connect to a backend service.
 *
 * @param message The user-facing error message.
 * @param debugMessage A detailed message intended for debugging purposes.
 */
class BackendConnectionException(message: String, debugMessage: String) : NetworkException(
    message = message,
    debugMessage = debugMessage
)

/**
 * Exception thrown when a network operation (like update or add) fails due to network-related issues.
 *
 * @param message The user-facing error message.
 * @param debugMessage A detailed message intended for debugging purposes.
 */
class NetworkOperationFailedException(message: String, debugMessage: String) : NetworkException(
    message = message,
    debugMessage = debugMessage
)

/**
 * Exception thrown when an update operation fails due to a network problem.
 *
 * @param message The user-facing error message.
 * @param debugMessage A detailed message intended for debugging purposes.
 */
class UpdateFailedException(message: String, debugMessage: String) : NetworkException(
    message = message,
    debugMessage = debugMessage
)

/**
 * Exception thrown when an attempt to add a new resource fails due to network-related problems.
 *
 * @param message The user-facing error message.
 * @param debugMessage A detailed message intended for debugging purposes.
 */
class AddFailedException(message: String, debugMessage: String) : NetworkException(
    message = message,
    debugMessage = debugMessage
)