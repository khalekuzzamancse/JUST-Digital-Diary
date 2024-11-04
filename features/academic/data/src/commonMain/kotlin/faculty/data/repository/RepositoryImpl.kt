@file:Suppress("functionName")

package faculty.data.repository

import core.database.factory.ApiFactory
import core.network.JsonParser
import faculty.data.EntityModelMapper
import faculty.data.entity.DepartmentReadEntity
import faculty.data.entity.FacultyReadEntity
import faculty.data.entity.TeacherReadEntity
import faculty.data.service.JsonHandler
import faculty.data.service.withExceptionHandle
import faculty.domain.model.TeacherReadModel
import faculty.domain.model.DepartmentReadModel
import faculty.domain.model.FacultyReadModel
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
class RepositoryImpl internal constructor(
    private val jsonParser: JsonParser,
    private val handler: JsonHandler,
    private val token:String?
) : Repository {
    private val api= ApiFactory.academicApi(token)
    override suspend fun getFaculties(): Result<List<FacultyReadModel>> {
        return with(handler) {
            withExceptionHandle {
                val json = api.readAllFaculty()

                /** Execution is here means server sent a response we have to parse it
                 * - 3 possible cases: We got excepted  json or Json is a server message in format ServerResponseMessageEntity  or  Server send a json that format is not known yet,may be server change it json format or other
                 */
                if (json._isFacultyListEntity()) {
                    val entity = json.parseOrThrow(ListSerializer(FacultyReadEntity.serializer()))
                    return Result.success(
                        with(EntityModelMapper) {
                            entity.sortedBy { it.priority }.map { it.toModel() }
                        }
                    )
                } else
                    return Result.failure(json.parseAsServerMessageOrThrow())
            }
        }


    }


    override suspend fun getTeachers(deptId: String): Result<List<TeacherReadModel>> {
        return with(handler) {
            withExceptionHandle {
                val json = api.readTeachersUnderDept(deptId)
                if (json._isTeacherListEntity()) {

                    val entities =
                        json.parseOrThrow(ListSerializer(TeacherReadEntity.serializer()))

                    return Result.success(
                        with(EntityModelMapper) {
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
    ): Result<List<DepartmentReadModel>> {

        return with(handler) {
            withExceptionHandle {
                val json = api.readDeptsUnderFaculty(facultyId)

                if (json._isDepartmentListEntity()) {
                    val entities =
                        json.parseOrThrow(ListSerializer(DepartmentReadEntity.serializer()))
                    return Result.success(
                        with(EntityModelMapper) {
                            entities.sortedBy { it.priority }.map { it.toModel() }
                        }
                    )
                } else
                    return Result.failure(json.parseAsServerMessageOrThrow())
            }
        }

    }

    private fun String._isFacultyListEntity() =
        jsonParser.parse(this, ListSerializer(FacultyReadEntity.serializer())).isSuccess

    private fun String._isDepartmentListEntity() =
        jsonParser.parse(this, ListSerializer(DepartmentReadEntity.serializer())).isSuccess

    private fun String._isTeacherListEntity() =
        jsonParser.parse(this, ListSerializer(TeacherReadEntity.serializer())).isSuccess


}
