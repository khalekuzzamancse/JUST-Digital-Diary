@file:Suppress("functionName")

package data.repository

import core.database.api.ApiFactory
import core.network.JsonParser
import data.ModelMapper
import data.entity.admin.DepartmentEntryEntity
import data.entity.admin.FacultyEntryEntity
import data.entity.admin.TeacherEntryEntity
import data.service.JsonHandler
import data.service.withExceptionHandle
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
    private val handler: JsonHandler,
) : Repository {

    override suspend fun getFaculties(): Result<List<FacultyModel>> {
        return with(handler) {
            withExceptionHandle {
                val json = ApiFactory.academicAdminApi().readAllFaculty()
                /** Execution is here means server sent a response we have to parse it
                 * - 3 possible cases: We got excepted  json or Json is a server message in format ServerResponseMessageEntity  or  Server send a json that format is not known yet,may be server change it json format or other
                 */
                if (json._isFacultyListEntity()) {
                    val entity = json.parseOrThrow(ListSerializer(FacultyEntryEntity.serializer()))
                    return Result.success(
                        with(ModelMapper) {
                            entity.sortedBy { it.priority }.map { it.toModel() }
                        }
                    )
                } else
                    return Result.failure(json.parseAsServerMessageOrThrow())
            }
        }


    }


    override suspend fun getTeachers(deptId: String): Result<List<TeacherModel>> {
        return with(handler) {
            withExceptionHandle {
                val json = ApiFactory.academicAdminApi().readTeachersUnderDept(deptId)
                if (json._isTeacherListEntity()) {
                    val entities =
                        json.parseOrThrow(ListSerializer(TeacherEntryEntity.serializer()))
                    return Result.success(
                        with(ModelMapper) {
                            entities.sortedBy { it.priority }.map { it.toModel() }
                        }
                    )
                } else
                    return Result.failure(json.parseAsServerMessageOrThrow())
            }
        }
    }

    override suspend fun getDepartment(
        facultyId: String
    ): Result<List<DepartmentModel>> {

        return with(handler) {
            withExceptionHandle {
                val json = ApiFactory.academicAdminApi().deptUnderFaculty(facultyId)

                if (json._isDepartmentListEntity()) {
                    val entities =
                        json.parseOrThrow(ListSerializer(DepartmentEntryEntity.serializer()))
                    return Result.success(
                        with(ModelMapper) {
                            entities.sortedBy { it.priority }.map { it.toModel() }
                        }
                    )
                } else
                    return Result.failure(json.parseAsServerMessageOrThrow())
            }
        }

    }

    private fun String._isFacultyListEntity() =
        jsonParser.parse(this, ListSerializer(FacultyEntryEntity.serializer())).isSuccess

    private fun String._isDepartmentListEntity() =
        jsonParser.parse(this, ListSerializer(DepartmentEntryEntity.serializer())).isSuccess

    private fun String._isTeacherListEntity() =
        jsonParser.parse(this, ListSerializer(TeacherEntryEntity.serializer())).isSuccess


}
