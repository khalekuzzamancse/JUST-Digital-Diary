package auth.data.factory

import auth.data.entity.LoginResponseEntity
import auth.data.entity.ServerResponseMessageEntity
import auth.data.repository.JsonHandler
import auth.domain.exception.CustomException
import core.network.JsonParser
import core.network.NetworkException
/**
 * - See[JsonHandler] docs
 */

internal class JsonHandlerImpl(private val jsonParser: JsonParser) : JsonHandler {

    override fun parseServerMessage(json: String): Result<String> {
        return if (json.isServerMessage())
            Result.failure(json.createServerMessageException())
        else
            Result.failure(CustomException.JsonParseException(json))
    }

    override fun handleFailure(exception: Throwable): Result<String> {
        return when (exception) {
            is NetworkException -> Result.failure(
                CustomException.NetworkIOException(
                    message = exception.message,
                    debugMessage = exception.debugMessage
                )
            )
            else -> Result.failure(CustomException.UnKnownException(exception))
        }
    }
    override fun parseAsServerMessageOrThrowCustomException(json: String): CustomException {
        return if (json.isServerMessage())
            json.createServerMessageException()
        else
            CustomException.JsonParseException(json)
    }

    override fun createCustomException(exception: Throwable): CustomException {
        return when (exception) {
            is NetworkException ->
                CustomException.NetworkIOException(
                    message = exception.message,
                    debugMessage = exception.debugMessage
                )

            else -> CustomException.UnKnownException(exception)
        }
    }

    // Helper methods to process JSON strings
    private fun String.createServerMessageException(): CustomException.MessageFromServer {
        val entity = jsonParser.parse(this, ServerResponseMessageEntity.serializer()).getOrThrow()
        return CustomException.MessageFromServer(message = entity.message)
    }

    private fun String.isServerMessage(): Boolean =
        jsonParser.parse(this, ServerResponseMessageEntity.serializer()).isSuccess

    override fun isLoginResponse(json: String): Boolean =
        jsonParser.parse(json, LoginResponseEntity.serializer()).isSuccess

    override fun parseAsLoginResponse(json: String): LoginResponseEntity =
        jsonParser.parse(json, LoginResponseEntity.serializer()).getOrThrow()
}
