@file:Suppress("functionName", "unused")

package auth.data.repository

import auth.data.dto.LoginModelDTO
import auth.data.entity.LoginResponseEntity
import auth.data.entity.ServerResponseMessageEntity
import auth.domain.exception.CustomException
import auth.domain.model.LoginModel
import auth.domain.repository.LoginRepository
import component.ApiServiceClient
import component.JsonParser

/**
 * - Prevent consumer module to create direct instance to reduce coupling instead create
 * instance via `factory method`
 */
class LoginRepositoryImpl internal constructor(
    private val apiServiceClient: ApiServiceClient,
    private val jsonParser: JsonParser
) : LoginRepository {
    override suspend fun login(model: LoginModel): Result<String> {
        val url = "https://backend.rnzgoldenventure.com/api/users/login"
        apiServiceClient
            .post(url, LoginModelDTO.loginModelToEntity(model))
            .fold(
                onSuccess = { json ->
                    /** Execution is here means server sent a response we have to parse it
                     * - 3 possible cases:
                     * - We got excepted  json
                     * - or Json is a server message in format ServerResponseMessageEntity that want to tell us something, there may be some error
                     * - or Server send a json that format is not known yet,may be server change it json format or other
                     */
                    try {
                        if (json._isLoginResponse())
                            return Result.success(json._parseAsLoginResponse().token)
                        else if (json._isServerMessage())
                            return Result.failure(json._createServerMessageException())
                        else
                            return Result.failure(CustomException.JsonParseException(json))

                    } catch (e: Exception) {
                        return Result.failure(CustomException.JsonParseException(json))
                    }


                },
                onFailure = { exception ->
                    //Execution is here means we not get any response
                    return Result.failure(
                        CustomException.ServerConnectingException(exception)
                    )
                }
            )

    }

    //TODO:Helper method section

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