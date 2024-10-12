package data.repository

import core.database.api.ApiFactory
import core.network.JsonParser
import data.Mapper
import data.entity.admin.DepartmentResponseEntity
import data.entity.admin.TeacherResponseEntity
import data.entity.public_.FacultyEntity
import data.service.JsonHandler
import faculty.domain.model.public_.DepartmentModel
import faculty.domain.model.public_.FacultyModel
import faculty.domain.model.public_.TeacherModel
import faculty.domain.repository.Repository
import kotlinx.serialization.builtins.ListSerializer

/**
 * - This for fetch data from `database` module
 * - This class receives all dependencies via the constructor, making it easy to integrate
 * with Dependency Injection (DI)
 * - It depends on  abstraction so via the `factory method` any time it dependencies can be altered with different
 * implementations
 *- Should not create instances of it via the constructor; instead,
 * use a factory method or dependency injection (DI) to obtain an instance
 *
 */
class RepositoryImpl2 internal constructor(
    private val jsonParser: JsonParser,
    private val jsonHandler: JsonHandler,
) : Repository {

    override suspend fun getFaculties(): Result<List<FacultyModel>> {
        try {
            val json = ApiFactory.academicAdminApi().getAllFaculties()
            /** Execution is here means server sent a response we have to parse it
             * - 3 possible cases: We got excepted  json or Json is a server message in format ServerResponseMessageEntity  or  Server send a json that format is not known yet,may be server change it json format or other
             */
            if (json._isFacultyListEntity()) {
                val entity =
                    jsonParser.parseOrThrow(json, ListSerializer(FacultyEntity.serializer()))
                return Result.success(Mapper.toFacultyModel(entity))
            } else
                return Result.failure(jsonHandler.parseAsServerMessageOrThrowCustomException(json))

        } catch (exception: Throwable) {
            return Result.failure(jsonHandler.createCustomException(exception))
        }
    }

    override suspend fun getTeachers(deptId: String): Result<List<TeacherModel>> {
        try {

            val json = ApiFactory.academicAdminApi().getTeachers()
            println("Teachers")
            println("TeacherEntity:${json._isTeacherListEntity()}")
            if (json._isTeacherListEntity()) {
                val entities = jsonParser.parseOrThrow(
                    json,
                    ListSerializer(TeacherResponseEntity.serializer())
                )
                return Result.success(
                    with(Mapper) {
                        entities.sortedBy { it.priority }.map { it.toModel() }
                    }
                )
            } else
                return Result.failure(jsonHandler.parseAsServerMessageOrThrowCustomException(json))
        } catch (exception: Throwable) {
            return Result.failure(jsonHandler.createCustomException(exception))
        }

    }

    override suspend fun getDepartment(
        facultyId: String
    ): Result<List<DepartmentModel>> {
        try {
            val json = ApiFactory.academicAdminApi().getDepartments()

            if (json._isDepartmentListEntity()) {
                val entities = jsonParser.parseOrThrow(
                    json,
                    ListSerializer(DepartmentResponseEntity.serializer())
                )
                return Result.success(
                    with(Mapper) {
                        entities.sortedBy { it.priority }.map { it.toModel() }
                    }
                )
            } else
                return Result.failure(jsonHandler.parseAsServerMessageOrThrowCustomException(json))

        } catch (exception: Throwable) {
            return Result.failure(jsonHandler.createCustomException(exception))
        }

    }

    private fun String._isFacultyListEntity() =
        jsonParser.parse(this, ListSerializer(FacultyEntity.serializer())).isSuccess

    private fun String._isDepartmentListEntity() =
        jsonParser.parse(this, ListSerializer(DepartmentResponseEntity.serializer())).isSuccess

    private fun String._isTeacherListEntity() =
        jsonParser.parse(this, ListSerializer(TeacherResponseEntity.serializer())).isSuccess


}
