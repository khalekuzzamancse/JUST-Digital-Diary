package administration.data.service

import admin_office.domain.exception.CustomException

/**
 * After implementing the  repositories, we identified duplicate and common code
 * in both implementations. During refactoring, we introduced this handler to extract the shared logic.
 * This handler is optional and was added to improve maintainability by reducing redundancy.
 */


internal interface JsonHandler {
    /**
     * Try to parse as ServerMessage,if parse fails then return
     */
    fun parseAsServerMessageOrThrowCustomException(json: String): CustomException
    fun createCustomException(exception: Throwable): CustomException

}
