@file:Suppress("functionName", "unused")

package auth.data.repository

import auth.data.dto.LoginModelDTO
import auth.data.entity.ResetPasswordCodeValidateEntity
import auth.data.entity.ResetPasswordConfirmEntity
import auth.data.entity.ResetPasswordRequestEntity
import auth.domain.model.LoginModel
import auth.domain.model.ResetPasswordCodeValidateModel
import auth.domain.model.ResetPasswordModel
import auth.domain.repository.LoginRepository
import core.network.ApiServiceClient

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
    private val responseHandler: JsonHandler// Injected Helper
) : LoginRepository {

    override suspend fun login(model: LoginModel): Result<String> {
        val url = "https://backend.rnzgoldenventure.com/api/users/login"
        return try {

            /** Execution is here means server sent a response we have to parse it
            //         * - 3 possible cases:
            //         * - We got excepted  json
            //         * - or Json is a server message in format ServerResponseMessageEntity that want to tell us something, there may be some error
            //         * - or Server send a json that format is not known yet,may be server change it json format or other
            //         */
            val json = apiServiceClient.postOrThrow(url, LoginModelDTO.loginModelToEntity(model))
            if (responseHandler.isLoginResponse(json))
                Result.success(responseHandler.parseAsLoginResponse(json).token)
            else
                responseHandler.parseServerMessage(json)


        } catch (exception: Throwable) {
            //Execution is here means  not get any response most probably failed to connect with server
            responseHandler.handleFailure(exception)
        }
    }

    override suspend fun sendResetPasswordRequest(email: String): Result<Unit> {
        val url = "https://backend.rnzgoldenventure.com/api/users/forgot-password"
        try {
            val json = apiServiceClient.postOrThrow(url, ResetPasswordRequestEntity(email))
            return Result.failure(responseHandler.parseAsServerMessageOrThrowCustomException(json))

        } catch (exception: Throwable) {
            return Result.failure(responseHandler.createCustomException(exception))
        }
    }


    override suspend fun resetPassword(model: ResetPasswordModel): Result<Unit> {
        val validate = "https://backend.rnzgoldenventure.com/api/users/validate-code"
        val url = "https://backend.rnzgoldenventure.com/api/users/set-new-password"
        try {
            apiServiceClient.postOrThrow(
                url = validate, body = ResetPasswordCodeValidateEntity(
                    email = model.email,
                    code = model.code
                )
            )
            val json = apiServiceClient.postOrThrow(
                url, ResetPasswordConfirmEntity(
                    email = model.email, password = model.newPassword, code = model.code
                )
            )
            return Result.failure(responseHandler.parseAsServerMessageOrThrowCustomException(json))

        } catch (exception: Throwable) {
            return Result.failure(responseHandler.createCustomException(exception))
        }
    }

    override suspend fun validateResetPasswordCode(model: ResetPasswordCodeValidateModel): Result<Unit> {
        val validate = "https://backend.rnzgoldenventure.com/api/users/validate-code"
        try {
            val json = apiServiceClient.postOrThrow(
                url = validate, body = ResetPasswordCodeValidateEntity(
                    email = model.email,
                    code = model.code
                )
            )
            return Result.failure(responseHandler.parseAsServerMessageOrThrowCustomException(json))

        } catch (exception: Throwable) {
            return Result.failure(responseHandler.createCustomException(exception))
        }
    }
}
