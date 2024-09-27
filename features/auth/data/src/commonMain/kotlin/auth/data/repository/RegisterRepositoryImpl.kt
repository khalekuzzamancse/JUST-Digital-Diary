@file:Suppress("functionName", "unused")

package auth.data.repository

import auth.data.dto.RegisterModelDTO
import auth.data.factory.API
import auth.domain.model.AccountVerifyModel
import auth.domain.model.RegisterModel
import auth.domain.repository.RegisterRepository
import core.network.ApiServiceClient

/**
 * - Prevent consumer module to create direct instance to reduce coupling instead create
 * instance via `factory method`
 */
class RegisterRepositoryImpl internal constructor(
    private val apiServiceClient: ApiServiceClient,
    private val handler: JsonHandler // Injected Helper
) : RegisterRepository {

    override suspend fun register(model: RegisterModel): Result<String> {
        return try {
            /* Execution is here means server sent a response we have to parse it
             * - 2 possible cases:
             * - Json is a server message in format ServerResponseMessageEntity that want to tell us something,
             *  there may be some error such as email already exists or tell to verify the email etc or may be success
             * - or Server send a json that format is not known yet,may be server change it json format or other
             */
            val json = apiServiceClient.postOrThrow(API.REGISTER, RegisterModelDTO.toEntity(model))
            handler.parseServerMessage(json)
        } catch (exception: Throwable) {
            //Execution is here means  not get any response most probably failed to connect with server
            handler.handleFailure(exception)
        }
    }

    override suspend fun verifyAccount(model: AccountVerifyModel): Result<String> {
        return try {

            val json = apiServiceClient.postOrThrow(API.ACCOUNT_VERIFY, RegisterModelDTO.toEntity(model))
            handler.parseServerMessage(json)
        } catch (exception: Throwable) {
            handler.handleFailure(exception)
        }
    }
}
