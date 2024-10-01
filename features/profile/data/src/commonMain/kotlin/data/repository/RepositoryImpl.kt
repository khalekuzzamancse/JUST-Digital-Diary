@file:Suppress("functionName","unused")
package data.repository

import core.network.ApiServiceClient
import core.network.Header
import core.network.JsonParser
import data.entity.EntityMapper
import data.entity.ProfileEntity
import data.services.JsonHandler
import domain.model.ProfileModel
import domain.repository.Repository

class RepositoryImpl internal constructor(
    private val token: String,
    private val apiServiceClient: ApiServiceClient,
    private val jsonParser: JsonParser,
    private val jsonHandler: JsonHandler
) : Repository {
    override suspend fun retrieveProfile(): Result<ProfileModel> {
        val url=""
        try {
            val json = apiServiceClient.retrieveJsonOrThrow(url, Header(key = "Authorization", value = token))
            /** Execution is here means server sent a response we have to parse it
             * - 3 possible cases: We got excepted  json or Json is a server message in format ServerResponseMessageEntity  or  Server send a json that format is not known yet,may be server change it json format or other
             */
            if (json._isProfileEntity()) {
                val entity = jsonParser.parseOrThrow(json, ProfileEntity.serializer())
                return Result.success(EntityMapper.toModel(entity))
            } else
                return Result.failure(jsonHandler.parseAsServerMessageOrThrowCustomException(json))

        } catch (exception: Throwable) {
            return Result.failure(jsonHandler.createCustomException(exception))
        }
    }

    private fun String._isProfileEntity() =
        jsonParser.parse(this, ProfileEntity.serializer()).isSuccess

}