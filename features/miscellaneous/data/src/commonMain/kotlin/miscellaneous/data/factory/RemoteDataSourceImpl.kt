@file:Suppress("functionName")

package miscellaneous.data.factory

import core.network.ApiServiceClient
import core.network.Header
import core.network.JsonParser
import kotlinx.serialization.builtins.ListSerializer
import miscellaneous.data.entity.EntityMapper
import miscellaneous.data.entity.EventGalleyEntity
import miscellaneous.data.entity.VCInfoEntity
import miscellaneous.data.services.JsonHandler
import miscellaneous.data.source.RemoteDataSource
import miscellaneous.domain.model.EventGalleryModel
import miscellaneous.domain.model.VCInfoModel

class RemoteDataSourceImpl internal constructor(
    private val apiServiceClient: ApiServiceClient,
    private val jsonParser: JsonParser,
    private val jsonHandler: JsonHandler
) : RemoteDataSource {
    override suspend fun getVCInfo(token: String): Result<VCInfoModel> {
        val url = "TODO()"
        try {
            val json = apiServiceClient.readJsonOrThrow(
                url,
                Header(key = "Authorization", value = token)
            )
            /** Execution is here means server sent a response we have to parse it
             * - 3 possible cases: We got excepted  json or Json is a server message in format ServerResponseMessageEntity  or  Server send a json that format is not known yet,may be server change it json format or other
             */
            if (json._isVcInfoEntity()) {
                val entity = jsonParser.parseOrThrow(json, VCInfoEntity.serializer())
                return Result.success(EntityMapper.toModel(entity))
            } else
                return Result.failure(jsonHandler.parseAsServerMessageOrThrowCustomException(json))

        } catch (exception: Throwable) {
            return Result.failure(jsonHandler.createCustomException(exception))
        }

    }

    override suspend fun getEvents(token: String): Result<List<EventGalleryModel>> {
        val url = "TODO()"
        try {
            val json = apiServiceClient.readJsonOrThrow(
                url,
                Header(key = "Authorization", value = token)
            )
            /** Execution is here means server sent a response we have to parse it
             * - 3 possible cases: We got excepted  json or Json is a server message in format ServerResponseMessageEntity  or  Server send a json that format is not known yet,may be server change it json format or other
             */
            if (json._isEventEntity()) {
                val entity = jsonParser.parseOrThrow(json, ListSerializer(EventGalleyEntity.serializer()))
                return Result.success(entity.map { EntityMapper.toModel(it) })
            } else
                return Result.failure(jsonHandler.parseAsServerMessageOrThrowCustomException(json))

        } catch (exception: Throwable) {
            return Result.failure(jsonHandler.createCustomException(exception))
        }
    }

    private fun String._isVcInfoEntity() =
        jsonParser.parse(this, VCInfoEntity.serializer()).isSuccess

    private fun String._isEventEntity() =
        jsonParser.parse(this, ListSerializer(EventGalleyEntity.serializer())).isSuccess
}