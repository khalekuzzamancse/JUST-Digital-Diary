@file:Suppress("UnUsed")
package academic_calender_domain.exception

/**
 *  - Represents the `data structure` that will be used to send and receive `data` to and from the `:ui` and `:data` modules
 *  - Consumer modules should not directly use this for their own purposes; for example, the `view/UI` should not use it as `viewData`,
 *   and the `:data` module should use it as an `entity` or `schema` to avoid tight coupling with this module
 *   - This `model` is for the `domain` module only, where the `domain` module is the implemented version of Clean Architecture's `application` layer
 *   - The `:ui` module should convert this `model` to a UI-friendly model via a `Presenter` before using it

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