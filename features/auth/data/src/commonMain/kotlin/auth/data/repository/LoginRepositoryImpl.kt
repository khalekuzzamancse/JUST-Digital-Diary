@file:Suppress("functionName", "unused")

package auth.data.repository

import auth.data.dto.LoginModelDTO
import auth.data.entity.LoginResponseEntity
import auth.data.entity.ServerResponseMessageEntity
import auth.domain.exception.CustomException
import auth.domain.model.LoginModel
import auth.domain.repository.LoginRepository
import core.network.ApiServiceClient
import core.network.JsonParser
import core.network.NetworkException

/**
 * - This class receives all dependencies via the constructor, making it easy to integrate
 * with Dependency Injection (DI)
 * - It depends on  abstraction so via the `factory method` any time it dependencies can be altered with different
 * implementations
 *- Should not create instances of it via the constructor; instead,
 * use a factory method or dependency injection (DI) to obtain an instance
 */
class LoginRepositoryImpl internal constructor(
    private val apiServiceClient: ApiServiceClient,
    private val jsonParser: JsonParser
) : LoginRepository {
    override suspend fun login(model: LoginModel): Result<String> {
        val url = "https://backend.rnzgoldenventure.com/api/users/login"
        return try {
            val json = apiServiceClient.postOrThrow(url, LoginModelDTO.loginModelToEntity(model))
            _returnParsedJsonOrFailure(json)

        } catch (exception: Throwable) {
            //Execution is here means  not get any response most probably failed to connect with server
            _toResultWithCustomFailure(exception)
        }

    }


    private fun _returnParsedJsonOrFailure(json: String): Result<String> {
        /** Execution is here means server sent a response we have to parse it
         * - 3 possible cases:
         * - We got excepted  json
         * - or Json is a server message in format ServerResponseMessageEntity that want to tell us something, there may be some error
         * - or Server send a json that format is not known yet,may be server change it json format or other
         */
        return if (json._isLoginResponse())
            Result.success(json._parseAsLoginResponse().token)
        else if (json._isServerMessage())
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

    private fun String._isLoginResponse() =
        jsonParser.parse(this, LoginResponseEntity.serializer()).isSuccess

    private fun String._parseAsLoginResponse() =
        jsonParser.parse(this, LoginResponseEntity.serializer()).getOrThrow()


}