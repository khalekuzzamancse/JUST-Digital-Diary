package data.misc

import feature.academiccalender.domain.exception.CustomException
import kotlinx.serialization.KSerializer

/**
 * After implementing the  repositories, we identified duplicate and common code
 * in both implementations. During refactoring, we introduced this handler to extract the shared logic.
 * This handler is optional and was added to improve maintainability by reducing redundancy.
 */


internal interface JsonHandler {
    /**
     * Try to parse as ServerMessage,if parse fails then return
     *
     */
    @Throws (CustomException::class)
    fun parseAsServerMessageOrThrowCustomException(json: String): CustomException

    /**
     * - String or Context is the Json
     * - Introduced later to reduce boilerplate code and better syntax
     * - Can be Implemented as:
     * ```kotlin
     *  parseAsServerMessageOrThrowCustom(this)
     * ```
     */
    @Throws (CustomException::class)
    fun String.parseAsServerMessageOrThrow(): CustomException //Using different name to avoid Platform declaration clash
    /**
     * - String or Context is the Json
     * - Introduced later to reduce boilerplate code and better syntax
     * - Can be Implemented as:
     * ```kotlin
     *  jsonParser.parseOrThrow(this)
     * ```
     */
    fun <T> String.parseOrThrow(serializer: KSerializer<T>):T

    fun createCustomException(exception: Throwable): CustomException

}
