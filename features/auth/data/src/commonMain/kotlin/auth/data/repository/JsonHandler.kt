package auth.data.repository

import auth.data.entity.LoginResponseEntity
/**
 * After implementing the Login and Register repositories, we identified duplicate and common code
 * in both implementations. During refactoring, we introduced this handler to extract the shared logic.
 * This handler is optional and was added to improve maintainability by reducing redundancy.
 */


internal interface JsonHandler {
    fun parseServerMessage(json: String): Result<String>
    fun handleFailure(exception: Throwable): Result<String>
    fun isLoginResponse(json: String): Boolean
    fun parseAsLoginResponse(json: String): LoginResponseEntity
}
