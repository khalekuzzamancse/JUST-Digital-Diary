@file:Suppress("functionName", "unused")
package auth.data.repository

import auth.data.dto.RegisterModelDTO
import auth.data.entity.ServerResponseMessageEntity
import auth.domain.exception.CustomException
import auth.domain.model.RegisterModel
import auth.domain.repository.RegisterRepository
import core.network.ApiServiceClient
import core.network.JsonParser
import core.network.NetworkException
/**
 * - Prevent consumer module to create direct instance to reduce coupling instead create
 * instance via `factory method`
 */
class RegisterRepositoryImpl internal  constructor(
    private val apiServiceClient: ApiServiceClient,
    private val jsonParser: JsonParser
) : RegisterRepository {
    private val url = "https://backend.rnzgoldenventure.com/api/users/register"

    override suspend fun register(model: RegisterModel): Result<String> {
        return try {
            val json = apiServiceClient.postOrThrow(url, RegisterModelDTO.toEntity(model))
            _returnParsedJsonOrFailure(json)

        } catch (exception: Throwable) {
            //Execution is here means  not get any response most probably failed to connect with server
            _toResultWithCustomFailure(exception)
        }
    }
    private fun _returnParsedJsonOrFailure(json: String): Result<String> {
        /** Execution is here means server sent a response we have to parse it
         * - 2 possible cases:
         * - Json is a server message in format ServerResponseMessageEntity that want to tell us something,
         *  there may be some error such as email already exists or tell to verify the email etc or may be success
         * - or Server send a json that format is not known yet,may be server change it json format or other
         */
        //as per design the registration will never success until verification
       return  if (json._isServerMessage())
            Result.failure(json._createServerMessageException())
        else
            Result.failure(CustomException.JsonParseException(json))
    }

    private fun _toResultWithCustomFailure(exc: Throwable): Result<String> {
        return when (exc) {
            is NetworkException -> Result.failure(
                CustomException.NetworkIOException(
                    message = exc.message,
                    debugMessage = exc.debugMessage
                )
            )

            else -> Result.failure(CustomException.UnKnownException(exc))
        }
    }

    //TODO:Helper method section----------
    //TODO:Helper method section----------
    private fun String._createServerMessageException(): CustomException.MessageFromServer {
        val entity = jsonParser.parse(this, ServerResponseMessageEntity.serializer()).getOrThrow()
        return CustomException.MessageFromServer(message = entity.message)
    }

    private fun String._isServerMessage() =
        jsonParser.parse(this, ServerResponseMessageEntity.serializer()).isSuccess


}