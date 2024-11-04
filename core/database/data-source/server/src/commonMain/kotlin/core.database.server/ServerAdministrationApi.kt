@file:Suppress("functionName")
package core.database.server

import core.customexception.CustomException
import core.data.entity.administration.AdminOfficerReadEntity
import core.data.entity.administration.OfficeReadEntity
import core.data.entity.administration.SubOfficeReadEntity
import core.network.ApiServiceClient
import core.network.Header
import core.network.JsonParser
import domain.api.AdministrationApi
import domain.service.FeedbackMessageService
import core.database.server.entity.administration.AdminOfficeListEntity
import core.database.server.entity.administration.AdminOfficerListEntity
import core.database.server.entity.administration.SubOfficeListEntity

class ServerAdministrationApi(
    private val apiService: ApiServiceClient,
    private val parser: JsonParser,
    private val token: String,
    private val feedbackService:FeedbackMessageService
) : AdministrationApi {

    override suspend fun readOfficesOrThrow(): List<OfficeReadEntity> {
        val response = apiService.readJsonOrThrow(URL.ALL_OFFICE, Header(key = URL.KEY, value = token))

        if (response._isOfficeList()) {
            val responseEntity = parser.parseOrThrow(response, AdminOfficeListEntity.serializer())
          return responseEntity.offices.map { entity ->
                OfficeReadEntity(
                    priority = entity.id,
                    officeId = entity.office_id,
                    name = entity.name,
                    sub_offices_count = entity.sub_offices_count
                )
            }
        }
        //Not the OfficeList list entity , so try parse as feedback message or throws
        throw CustomException.ServerFeedbackExecpton(message = feedbackService.asFeedbackEntityOrThrow(response).message)
    }

    override suspend fun readSubOfficesOrThrow(officeId: String): List<SubOfficeReadEntity> {
        val response = apiService.readJsonOrThrow("${URL.SUB_OFFICE}/$officeId", Header(key = URL.KEY, value = token))

        if (response._isSubOfficeList()) {
            val responseEntity = parser.parseOrThrow(response, SubOfficeListEntity.serializer())
            return responseEntity.sub_offices.map { entity ->
                SubOfficeReadEntity(
                    priority = entity.id,
                    sub_office_id = entity.sub_office_id,
                    name = entity.name,
                    office_members_count = entity.office_members_count
                )
            }
        }
        //Not the OfficeList list entity , so try parse as feedback message or throws
        throw CustomException.ServerFeedbackExecpton(message = feedbackService.asFeedbackEntityOrThrow(response).message)
    }

    override suspend fun readEmployeesOrThrow(subOfficeId: String): List<AdminOfficerReadEntity> {
        val response = apiService.readJsonOrThrow("${URL.OFFICE_EMPLOYEE}/$subOfficeId", Header(key = URL.KEY, value = token))

        if (response._isTeacherListEntity()) {
            val responseEntity = parser.parseOrThrow(response, AdminOfficerListEntity.serializer())
            return responseEntity.officeMembers.map { entity ->
                AdminOfficerReadEntity(
                    priority = entity.type,
                    id = entity.uid,
                    name = entity.name,
                    email = entity.email,
                    additional_email = entity.additional_email,
                    profile = entity.profile,
                    phone = entity.phone,
                    achievement = entity.achievement,
                    designation = entity.offices.firstOrNull()?.designation?:"",
                    room_no =entity.offices.firstOrNull()?.room_no?:"",
                )
            }
        }
        //Not the OfficeList list entity , so try parse as feedback message or throws
        throw CustomException.ServerFeedbackExecpton(message = feedbackService.asFeedbackEntityOrThrow(response).message)
    }

    private fun String._isOfficeList() =
        parser.parse(this, AdminOfficeListEntity.serializer()).isSuccess

    private fun String._isSubOfficeList() =
        parser.parse(this, SubOfficeListEntity.serializer()).isSuccess

    private fun String._isTeacherListEntity() =
        parser.parse(this, AdminOfficerListEntity.serializer()).isSuccess
}