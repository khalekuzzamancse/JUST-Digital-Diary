@file:Suppress("functionName")

package faculty.data.repository

import core.database.factory.ApiFactory
import core.database.network.JsonParser
import faculty.data.EntityModelMapper
import faculty.data.entity.DepartmentReadEntity
import faculty.data.entity.FacultyReadEntity
import faculty.data.entity.TeacherReadEntity
import faculty.data.service.JsonHandler
import faculty.data.service.withExceptionHandle
import faculty.domain.model.DepartmentReadModel
import faculty.domain.model.DepartmentWriteModel
import faculty.domain.model.FacultyReadModel
import faculty.domain.model.FacultyWriteModel
import faculty.domain.model.TeacherReadModel
import faculty.domain.model.TeacherWriteModel
import faculty.domain.repository.AdminRepository
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class AdminRepositoryImpl internal constructor(
    private val handler: JsonHandler,
    private val jsonParser: JsonParser,
) : AdminRepository {
    private val api= ApiFactory.academicApi(null)
    private val jsonConverter = Json { prettyPrint = true }
    override suspend fun insertFaculty(model: FacultyWriteModel): Result<Unit> {
        return with(handler) {
            withExceptionHandle {
                val entity = with(EntityModelMapper) { model.toEntity() }
                val dataJson = jsonConverter.encodeToString(entity)

                /** Execution is here means server sent a response we have to parse it
                 * - 2 possible cases: We got excepted  json or Json is a server message in format ServerResponseMessageEntity  or  Server send a json that format is not known yet,may be server change it json format or other
                 */
                val responseJson =api.insertFaculty(dataJson)
                return Result.failure(responseJson.parseAsServerMessageOrThrow())
            }
        }
    }

    override suspend fun readFaculty(id: String): Result<FacultyReadModel> {
        return with(handler) {
            withExceptionHandle {
                val json = api.readFacultyById(id)
                if (json._isFacultyEntity()) {
                    val entity = json.parseOrThrow(FacultyReadEntity.serializer())
                    return Result.success(
                        with(faculty.data.EntityModelMapper) { entity.toModel() }
                    )
                } else
                    return Result.failure(json.parseAsServerMessageOrThrow())
            }
        }
    }


    override suspend fun readDept(id: String): Result<DepartmentReadModel> {
        return with(handler) {
            withExceptionHandle {
                val json = api.readDeptById(id)
                if (json._isDeptEntity()) {
                    val entity = json.parseOrThrow(DepartmentReadEntity.serializer())
                    return Result.success(
                        with(faculty.data.EntityModelMapper) { entity.toModel() }
                    )
                } else
                    return Result.failure(json.parseAsServerMessageOrThrow())

            }
        }
    }

    override suspend fun insertDept(model: DepartmentWriteModel): Result<Unit> {
        return with(handler) {
            withExceptionHandle {
                val entity = with(EntityModelMapper) { model.toEntity() }
                val dataJson = jsonConverter.encodeToString(entity)
                val responseJson = api.insertDept(model.facultyId, dataJson)
                return Result.failure(responseJson.parseAsServerMessageOrThrow())
            }
        }
    }


    override suspend fun readTeacher(id: String): Result<TeacherReadModel> {
        return with(handler) {
            withExceptionHandle {
                val json = api.readTeacherById(id)
                if (json._isTeacherEntity()) {
                    val entity = json.parseOrThrow(TeacherReadEntity.serializer())
                    return Result.success(
                        with(faculty.data.EntityModelMapper) { entity.toModel() }
                    )
                } else
                    return Result.failure(json.parseAsServerMessageOrThrow())
            }
        }
    }

    override suspend fun insertTeacher(model: TeacherWriteModel): Result<Unit> {
        return with(handler) {
            withExceptionHandle {
                val entity = with(EntityModelMapper) { model.toEntity() }
                val dataJson = jsonConverter.encodeToString(entity)
                val responseJson = api.insertTeacher(model.deptId, dataJson)

                return Result.failure(
                    responseJson.parseAsServerMessageOrThrow()
                )
            }
        }
    }


    override suspend fun getAllDept(): Result<List<DepartmentReadModel>> {
        return with(handler) {
            withExceptionHandle {
                val json =api.readAllDept()

                if (json._isDepartmentListEntity()) {
                    val entities =
                        json.parseOrThrow(ListSerializer(DepartmentReadEntity.serializer()))
                    return Result.success(
                        with(faculty.data.EntityModelMapper) {
                            entities.sortedBy { it.priority }.map { it.toModel() }
                        }
                    )
                } else
                    return Result.failure(json.parseAsServerMessageOrThrow())

            }
        }

    }

    //TODO:UPDATE OPERATIONS---------UPDATE OPERATIONS
    //TODO:UPDATE OPERATIONS---------UPDATE OPERATIONS
    //TODO:UPDATE OPERATIONS---------UPDATE OPERATIONS
    override suspend fun updateFaculty(facultyId: String, model: FacultyWriteModel): Result<Unit> {
        return with(handler) {
            withExceptionHandle {
                val entity = with(EntityModelMapper) { model.toEntity() }
                val dataJson = jsonConverter.encodeToString(entity)
                val responseJson =api.updateFaculty(
                    facultyId = facultyId,
                    json = dataJson
                )
                return Result.failure(responseJson.parseAsServerMessageOrThrow())
            }
        }
    }

    override suspend fun updateDepartment(
        deptId: String,
        model: DepartmentWriteModel
    ): Result<Unit> {
        return with(handler) {
            withExceptionHandle {
                val entity = with(EntityModelMapper) { model.toEntity() }
                val dataJson = jsonConverter.encodeToString(entity)
                val responseJson = api.updateDept(
                    deptId = deptId,
                    json = dataJson
                )
                return Result.failure(responseJson.parseAsServerMessageOrThrow())
            }
        }
    }

    override suspend fun updateTeacher(teacherId: String, model: TeacherWriteModel): Result<Unit> {
        return with(handler) {
            withExceptionHandle {
                val entity = with(EntityModelMapper) { model.toEntity() }
                val dataJson = jsonConverter.encodeToString(entity)
                val responseJson = api.updateTeacher(
                    teacherId = teacherId,
                    json = dataJson
                )
                return Result.failure(
                    responseJson.parseAsServerMessageOrThrow()
                )
            }
        }
    }

    override suspend fun deleteFaculty(facultyId: String): Result<Unit> {
        return with(handler) {
            withExceptionHandle {
                val responseJson = api.deleteFaculty(facultyId)
                return Result.failure(
                    responseJson.parseAsServerMessageOrThrow()
                )
            }
        }
    }

    override suspend fun deleteDepartment(deptId: String): Result<Unit> {
        return with(handler) {
            withExceptionHandle {
                val responseJson =api.deleteDepartment(deptId)
                return Result.failure(
                    responseJson.parseAsServerMessageOrThrow()
                )
            }
        }
    }

    override suspend fun deleteTeacher(teacherId: String): Result<Unit> {
        return with(handler) {
            withExceptionHandle {
                val responseJson = api.deleteTeacher(teacherId)
                return Result.failure(
                    responseJson.parseAsServerMessageOrThrow()
                )
            }
        }
    }


    private fun String._isFacultyEntity() =
        jsonParser.parse(this, FacultyReadEntity.serializer()).isSuccess

    private fun String._isDepartmentListEntity() =
        jsonParser.parse(this, ListSerializer(DepartmentReadEntity.serializer())).isSuccess

    private fun String._isDeptEntity() =
        jsonParser.parse(this, DepartmentReadEntity.serializer()).isSuccess

    private fun String._isTeacherEntity() =
        jsonParser.parse(this, TeacherReadEntity.serializer()).isSuccess
}