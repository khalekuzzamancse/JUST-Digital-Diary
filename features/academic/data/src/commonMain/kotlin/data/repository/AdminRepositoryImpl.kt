package data.repository

import core.database.api.ApiFactory
import core.network.ApiServiceClient
import core.network.JsonParser
import data.Mapper
import data.entity.admin.DepartmentEntity
import data.entity.public_.FacultyEntity
import data.service.JsonHandler
import faculty.domain.model.admin.DepartmentEntryModel
import faculty.domain.model.admin.FacultyEntryModel
import faculty.domain.model.admin.TeacherEntryModel
import faculty.domain.model.public_.DepartmentModel
import faculty.domain.repository.AdminRepository
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class AdminRepositoryImpl internal constructor(
    private val apiService: ApiServiceClient,
    private val jsonHandler: JsonHandler,
    private val jsonParser: JsonParser,
) : AdminRepository {
    private val jsonConverter = Json { prettyPrint = true }
    override suspend fun addFaculty(model: FacultyEntryModel): Result<Unit> {
        try {
            val entity = with(Mapper) { model.toEntity() }
            val dataJson=jsonConverter.encodeToString(entity)
            val responseJson = ApiFactory.academicAdminApi().addFaculty(dataJson)
            /** Execution is here means server sent a response we have to parse it
             * - 2 possible cases: We got excepted  json or Json is a server message in format ServerResponseMessageEntity  or  Server send a json that format is not known yet,may be server change it json format or other
             */
            return Result.failure(jsonHandler.parseAsServerMessageOrThrowCustomException(responseJson))
        } catch (exception: Throwable) {
            return Result.failure(jsonHandler.createCustomException(exception))
        }
    }

    override suspend fun updateFaculty(model: FacultyEntryModel): Result<Unit> {
        TODO()
    }

    override suspend fun addDepartment(model: DepartmentEntryModel): Result<Unit> {
        try {
            val entity = with(Mapper) { model.toEntity() }
            val dataJson=jsonConverter.encodeToString(entity)
            val responseJson = ApiFactory.academicAdminApi().addDepartment(dataJson)
            /** Execution is here means server sent a response we have to parse it
             * - 2 possible cases: We got excepted  json or Json is a server message in format ServerResponseMessageEntity  or  Server send a json that format is not known yet,may be server change it json format or other
             */
            return Result.failure(jsonHandler.parseAsServerMessageOrThrowCustomException(responseJson))
        } catch (exception: Throwable) {
            return Result.failure(jsonHandler.createCustomException(exception))
        }
    }

    override suspend fun updateDepartment(model: DepartmentEntryModel): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun addTeacher(model: TeacherEntryModel): Result<Unit> {
        try {
            val entity = with(Mapper) { model.toEntity() }
            val dataJson=jsonConverter.encodeToString(entity)
            val responseJson = ApiFactory.academicAdminApi().addTeacher(dataJson)
            /** Execution is here means server sent a response we have to parse it
             * - 2 possible cases: We got excepted  json or Json is a server message in format ServerResponseMessageEntity  or  Server send a json that format is not known yet,may be server change it json format or other
             */
            return Result.failure(jsonHandler.parseAsServerMessageOrThrowCustomException(responseJson))
        } catch (exception: Throwable) {
            return Result.failure(jsonHandler.createCustomException(exception))
        }
    }


    override suspend fun updateTeacher(model: TeacherEntryModel): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllDept():Result< List<DepartmentModel>> {
        try {
            val json = ApiFactory.academicAdminApi().getDepartments()

            if (json._isDepartmentListEntity()) {
                val entities= jsonParser.parseOrThrow(json, ListSerializer(DepartmentEntity.serializer()))
                return Result.success(
                    with(Mapper){
                        entities.sortedBy { it.priority }.map { it.toModel()}
                    }
                )
            } else
                return Result.failure(jsonHandler.parseAsServerMessageOrThrowCustomException(json))

        } catch (exception: Throwable) {
            return Result.failure(jsonHandler.createCustomException(exception))
        }

    }
    private fun String._isDepartmentListEntity() =
        jsonParser.parse(this, ListSerializer(DepartmentEntity.serializer())).isSuccess
}