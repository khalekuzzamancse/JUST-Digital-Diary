@file:Suppress("LocalVariableName")
package core.customexception

/**
 * @param message for end user that is short enough
 * @param debugMessage for the developer that is for debugging
 */
data class ExceptionMessage(
    val message: String,
    val debugMessage: String
)

/**
 * - A safer alternative to `try-catch` blocks and `Result<T>`, with logging, an optional catch block, and reduced code
 */

object ErrorHandler {
    /**
     * - Provides a utility to handle and log any kind of exception with minimal code, using an optional catch block
     * - Helps reduce boilerplate code for exception handling
     *
     * # Motivation
     * - This `:core:custom-exception` module is designed for broad usage across modules, either directly via `implementation` dependency or indirectly via `api` dependency
     * - Often, you may want to ignore certain exceptions or catch any type of exception. This utility facilitates that process efficiently
     * - Centralizes exception handling in a single place, reducing the risk of catching an exception inappropriately as `Exception` or as another specific subtype that may not match the actual thrown exception. This can prevent crashes, especially when you are developing quickly or under pressure
     * - Additionally, this utility can serve as an alternative to using `Result<T>` by directly catching exceptions. Handling exceptions this way can be more stable, as unfolding `Result<T>` may throw exceptions and introduce hidden bugs or crashes
     */

    fun run(
        tag: String? = null,
        _catch: (exception: Throwable) -> Unit = {},
        _try: () -> Unit
    ) {
        try {
            _try()
        } catch (exception: Throwable) {
            _catch(exception)
            if (tag != null) {
                println("$tag:$exception")
            }
        }
    }

    /**
     * - For more info [run]
     */
   suspend fun runAsync(
       tag: String? = null,
       _catch: (exception: Throwable) -> Unit = {},
       _try:suspend () -> Unit
    ) {
        try {
            _try()
        } catch (exception: Throwable) {
            _catch(exception)
            if (tag != null) {
                println("$tag:$exception")
            }
        }
    }

    /**
     * - On failure give  the [ExceptionMessage]
     * - Another version of [run]
     *
     * # Motivation
     * - Almost every UI component needs to display a message in a snackbar on failure, requiring exception catching and conversion to a user-friendly message
     * - Since all modules depend on the custom-exception module, it is better to handle exceptions from a single source of truth
     */

    fun withCauseMsg(
        tag: String? = null,
        _catch: (msg: ExceptionMessage) -> Unit = {},
        _try: () -> Unit
    ) {
        try {
            _try()
        } catch (exception: Throwable) {
            val msg: ExceptionMessage = if (exception is CustomException)
                ExceptionMessage(
                    message = exception.message,
                    debugMessage = exception.debugMessage
                )
            else
                ExceptionMessage(
                    message = "Something is went wrong",
                    debugMessage = "${exception.message}+${exception.stackTraceToString()}"
                )
            if (tag != null) {
                println("$tag:$exception")
            }
            _catch(msg)
        }
    }

    /**
     * - More info [withCauseMsg]
     */
    fun withCauseMsgAsync(
        tag: String? = null,
        _catch: (msg: ExceptionMessage) -> Unit = {},
        _try: () -> Unit
    ) {
        try {
            _try()
        } catch (exception: Throwable) {
            val msg: ExceptionMessage = if (exception is CustomException)
                ExceptionMessage(
                    message = exception.message,
                    debugMessage = exception.debugMessage
                )
            else
                ExceptionMessage(
                    message = "Something is went wrong",
                    debugMessage = "${exception.message}+${exception.stackTraceToString()}"
                )
            if (tag != null) {
                println("$tag:$exception")
            }
            _catch(msg)
        }
    }
}