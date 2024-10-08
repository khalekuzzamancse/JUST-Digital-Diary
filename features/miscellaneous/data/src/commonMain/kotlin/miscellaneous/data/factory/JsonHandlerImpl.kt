package miscellaneous.data.factory


import core.network.JsonParser
import core.network.NetworkException
import miscellaneous.data.entity.ServerResponseMessageEntity
import miscellaneous.domain.exception.CustomException
import miscellaneous.data.services.JsonHandler

/**
 * - See[JsonHandler] docs
 */

internal class JsonHandlerImpl(private val jsonParser: JsonParser) : JsonHandler {

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


}
