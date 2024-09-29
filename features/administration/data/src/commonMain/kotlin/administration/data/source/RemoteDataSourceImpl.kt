@file:Suppress("FunctionName")

package administration.data.source

import admin_office.domain.model.AdminOfficerModel
import admin_office.domain.model.OfficeModel
import admin_office.domain.model.SubOfficeModel
import administration.data.Mapper
import administration.data.PackageLevelAccess
import administration.data.entity.AdminOfficeListEntity
import administration.data.entity.AdminOfficerListEntity
import administration.data.entity.SubOfficeListEntity
import administration.data.service.JsonHandler
import core.network.ApiServiceClient
import core.network.Header
import core.network.JsonParser

@OptIn(PackageLevelAccess::class)
class RemoteDataSourceImpl internal constructor(
    private val apiServiceClient: ApiServiceClient,
    private val jsonParser: JsonParser,
    private val jsonHandler: JsonHandler
) : RemoteDataSource {
    override suspend fun getOffices(token: String): Result<List<OfficeModel>> {
        val url = "https://backend.rnzgoldenventure.com/api/get/offices"
        try {
            val json = apiServiceClient.retrieveJsonOrThrow(
                url,
                Header(key = "Authorization", value = token)
            )
            /** Execution is here means server sent a response we have to parse it
             * - 3 possible cases: We got excepted  json or Json is a server message in format ServerResponseMessageEntity  or  Server send a json that format is not known yet,may be server change it json format or other
             */
            if (json._isOfficeList()) {
                val entity =
                    jsonParser.parseOrThrow(json, AdminOfficeListEntity.serializer())
                return Result.success(Mapper.officeEntityToModel(entity.offices))
            } else
                return Result.failure(jsonHandler.parseAsServerMessageOrThrowCustomException(json))

        } catch (exception: Throwable) {
            return Result.failure(jsonHandler.createCustomException(exception))
        }


    }

    override suspend fun getSubOffices(
        token: String,
        officeId: String
    ): Result<List<SubOfficeModel>> {
        val url = "https://backend.rnzgoldenventure.com/api/get/sub-offices/$officeId"
        try {
            val json = apiServiceClient.retrieveJsonOrThrow(
                url,
                Header(key = "Authorization", value = token)
            )
            if (json._isSubOfficeList()) {
                val entity = jsonParser.parseOrThrow(json, SubOfficeListEntity.serializer())
                return Result.success(Mapper.subOfficeEntityToModel(entity.sub_offices))
            } else
                return Result.failure(jsonHandler.parseAsServerMessageOrThrowCustomException(json))

        } catch (exception: Throwable) {
            return Result.failure(jsonHandler.createCustomException(exception))
        }
    }

    override suspend fun getOfficers(
        subOfficeId: String,
        token: String
    ): Result<List<AdminOfficerModel>> {
        val url = "https://backend.rnzgoldenventure.com/api/get/office-members/$subOfficeId"
        try {
            val json = apiServiceClient.retrieveJsonOrThrow(
                url,
                Header(key = "Authorization", value = token)
            )
            if (json._isTeacherListEntity()) {
                println("TeacherListEntity")
                val entity = jsonParser.parseOrThrow(json, AdminOfficerListEntity.serializer())
                return Result.success(Mapper.adminOfficersEntityToModel(entity.officeMembers))
            } else
                return Result.failure(jsonHandler.parseAsServerMessageOrThrowCustomException(json))

        } catch (exception: Throwable) {
            return Result.failure(jsonHandler.createCustomException(exception))
        }
    }


    private fun String._isOfficeList() =
        jsonParser.parse(this, AdminOfficeListEntity.serializer()).isSuccess

    private fun String._isSubOfficeList() =
        jsonParser.parse(this, SubOfficeListEntity.serializer()).isSuccess

    private fun String._isTeacherListEntity() =
        jsonParser.parse(this, AdminOfficerListEntity.serializer()).isSuccess

}