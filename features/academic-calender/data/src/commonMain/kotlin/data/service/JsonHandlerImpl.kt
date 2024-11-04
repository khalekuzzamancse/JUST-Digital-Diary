package data.service


import core.customexception.CustomException
import core.network.JsonParser
import core.network.NetworkException
import data.entity.FeedbackEntity


import kotlinx.serialization.KSerializer

/**
 * - See[JsonHandler] docs
 */

internal class JsonHandlerImpl(private val jsonParser: JsonParser) : JsonHandler {

    override fun parseAsServerMessageOrThrowCustomException(json: String): CustomException {
        return if (json.isServerMessage())
            json.createServerMessageException()
        else{
           CustomException.JsonParseException(json)

        }


    }

    override fun String.parseAsServerMessageOrThrow(): CustomException =
        parseAsServerMessageOrThrowCustomException(this)

    override fun <T> String.parseOrThrow(serializer: KSerializer<T>) =
        jsonParser.parseOrThrow(this, serializer)


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
        val entity = jsonParser.parse(this, FeedbackEntity.serializer()).getOrThrow()
        return CustomException.MessageFromServer(message = entity.message)
    }

    private fun String.isServerMessage(): Boolean =
        jsonParser.parse(this, FeedbackEntity.serializer()).isSuccess


}
