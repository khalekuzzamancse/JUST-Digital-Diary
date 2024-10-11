package data.repository

import core.network.ApiServiceClient
import data.Mapper
import data.service.JsonHandler
import faculty.domain.model.admin.DepartmentEntryModel
import faculty.domain.model.admin.FacultyEntryModel
import faculty.domain.model.admin.TeacherEntryModel
import faculty.domain.repository.AdminRepository

class AdminRepositoryImpl internal constructor(
    private val apiService: ApiServiceClient,
    private val jsonHandler: JsonHandler,
) : AdminRepository {
    override suspend fun addFaculty(model: FacultyEntryModel): Result<Unit> {
        val url=""
        try {
            val json = apiService.postOrThrow(url,Mapper.toFacultyEntryEntity(model))
            /** Execution is here means server sent a response we have to parse it
             * - 2 possible cases: We got excepted  json or Json is a server message in format ServerResponseMessageEntity  or  Server send a json that format is not known yet,may be server change it json format or other
             */
            return Result.failure(jsonHandler.parseAsServerMessageOrThrowCustomException(json))
        } catch (exception: Throwable) {
            return Result.failure(jsonHandler.createCustomException(exception))
        }
    }

    override suspend fun updateFaculty(model: FacultyEntryModel): Result<Unit> {
        TODO()
    }

    override suspend fun addDepartment(model: DepartmentEntryModel): Result<Unit> {
        val url=""
        try {
            val json = apiService.postOrThrow(url,Mapper.toDepartmentEntryEntity(model))
            /** Execution is here means server sent a response we have to parse it
             * - 2 possible cases: We got excepted  json or Json is a server message in format ServerResponseMessageEntity  or  Server send a json that format is not known yet,may be server change it json format or other
             */
            return Result.failure(jsonHandler.parseAsServerMessageOrThrowCustomException(json))
        } catch (exception: Throwable) {
            return Result.failure(jsonHandler.createCustomException(exception))
        }
    }

    override suspend fun updateDepartment(model: DepartmentEntryModel): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun addTeacher(model: TeacherEntryModel): Result<Unit> {
        val url=""
        try {
            val json = apiService.postOrThrow(url,Mapper.convertModelToEntity(model))
            /** Execution is here means server sent a response we have to parse it
             * - 2 possible cases: We got excepted  json or Json is a server message in format ServerResponseMessageEntity  or  Server send a json that format is not known yet,may be server change it json format or other
             */
            return Result.failure(jsonHandler.parseAsServerMessageOrThrowCustomException(json))
        } catch (exception: Throwable) {
            return Result.failure(jsonHandler.createCustomException(exception))
        }
    }

    override suspend fun updateTeacher(model: TeacherEntryModel): Result<Unit> {
        TODO("Not yet implemented")
    }
}