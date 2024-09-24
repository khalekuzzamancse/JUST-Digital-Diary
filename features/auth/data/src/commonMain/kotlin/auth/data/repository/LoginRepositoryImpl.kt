@file:Suppress("functionName", "unused")

package auth.data.repository

import auth.data.dto.LoginModelDTO
import auth.data.entity.LoginResponseEntity
import auth.data.entity.ServerErrorResponseEntity
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
                    /**
                     * - 3 possible case:
                     * - Json  is either LoginResponseSchema upon successful login
                     * - or Json is ServerResponseSchema upon login failure
                     * - or Server send a json that format is not known yet,may be server change it json format or other
                     */
                    var response = json._parseAsLoginResponseOrException()
                    if (response.isSuccess)
                        return response
                    response = json._parseAsServerErrorResponseOrException()
                    if (response.isSuccess)
                        return response
                    else
                        return Result.failure(CustomException.JsonParseException(json))

                },
                onFailure = { exception ->
                    return Result.failure(
                        CustomException.ServerConnectingException(exception)
                    )
                }
            )

    }



    //TODO:Helper method section
    private fun String._parseAsLoginResponseOrException(): Result<String> {
        val json = this
        jsonParser.parse(json, LoginResponseEntity.serializer())
            .fold(
                onSuccess = { schema ->
                    return Result.success(schema.token)
                },
                onFailure = { exception ->
                    return Result.failure(exception)
                }
            )
    }

    private fun String._parseAsServerErrorResponseOrException(): Result<String> {
        val json = this
        jsonParser.parse(json, ServerErrorResponseEntity.serializer())
            .fold(
                onSuccess = { schema ->
                    return Result.failure(
                        CustomException.ErrorFromServer(error = schema.message)
                    )
                },
                onFailure = { exception ->
                    return Result.failure(exception)
                }
            )
    }


}