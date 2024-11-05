package schedule.data.service
import core.customexception.CustomException
import core.customexception.NetworkException
import core.network.JsonParser
import kotlinx.serialization.KSerializer
import schedule.data.entity.ServerResponseMessageEntity

/**
 * - See[JsonHandler] docs
 */

internal class JsonHandlerImpl(private val jsonParser: JsonParser) : JsonHandler {

    override fun parseAsServerMessageOrThrowCustomException(json: String): CustomException {
        return if (json.isServerMessage())
            json.createServerMessageException()
        else{
            val error= CustomException.JsonParseException(json)
            println(error)
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
        val entity = jsonParser.parse(this, ServerResponseMessageEntity.serializer()).getOrThrow()
        return CustomException.MessageFromServer(message = entity.message)
    }

    private fun String.isServerMessage(): Boolean =
        jsonParser.parse(this, ServerResponseMessageEntity.serializer()).isSuccess


}
