@file:Suppress("functionName")

package data.repository

import core.database.factory.ApiFactory
import core.database.network.JsonParser
import data.ModelMapper
import data.ReadEntityModelMapper
import data.entity2.DepartmentReadEntity
import data.entity2.FacultyReadEntity
import data.entity2.TeacherReadEntity
import data.service.JsonHandler
import data.service.withExceptionHandle
import faculty.domain.model.TeacherReadModel
import faculty.domain.model.DepartmentWriteModel
import faculty.domain.model.FacultyWriteModel
import faculty.domain.model.TeacherWriteModel
import faculty.domain.model.DepartmentReadModel
import faculty.domain.model.FacultyReadModel
import faculty.domain.repository.AdminRepository
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class AdminRepositoryImpl internal constructor(
    private val handler: JsonHandler,
    private val jsonParser: JsonParser,
) : AdminRepository {
    private val jsonConverter = Json { prettyPrint = true }
    override suspend fun insertFaculty(model: FacultyWriteModel): Result<Unit> {
        return with(handler) {
            withExceptionHandle {
                val entity = with(ModelMapper) { model.toEntity() }
                val dataJson = jsonConverter.encodeToString(entity)

                /** Execution is here means server sent a response we have to parse it
                 * - 2 possible cases: We got excepted  json or Json is a server message in format ServerResponseMessageEntity  or  Server send a json that format is not known yet,may be server change it json format or other
                 */
                val responseJson = ApiFactory.academicApi().insertFaculty(dataJson)
                return Result.failure(responseJson.parseAsServerMessageOrThrow())
            }
        }
    }

    override suspend fun readFaculty(id: String): Result<FacultyReadModel> {
        return with(handler) {
            withExceptionHandle {
                val json = ApiFactory.academicApi().readFacultyById(id)
                if (json._isFacultyEntity()) {
                    val entity = json.parseOrThrow(FacultyReadEntity.serializer())
                    return Result.success(
                        with(ReadEntityModelMapper) { entity.toModel() }
                    )
                } else
                    return Result.failure(json.parseAsServerMessageOrThrow())
            }
        }
    }


    override suspend fun readDept(id: String): Result<DepartmentReadModel> {
        return with(handler) {
            withExceptionHandle {
                val json = ApiFactory.academicApi().readDeptById(id)
                if (json._isDeptEntity()) {
                    val entity = json.parseOrThrow(DepartmentReadEntity.serializer())
                    return Result.success(
                        with(ReadEntityModelMapper) { entity.toModel() }
                    )
                } else
                    return Result.failure(json.parseAsServerMessageOrThrow())

            }
        }
    }

    override suspend fun insertDept(model: DepartmentWriteModel): Result<Unit> {
        return with(handler) {
            withExceptionHandle {
                val entity = with(ModelMapper) { model.toEntity() }
                val dataJson = jsonConverter.encodeToString(entity)
                val responseJson = ApiFactory.academicApi().insertDept(model.facultyId, dataJson)
                return Result.failure(responseJson.parseAsServerMessageOrThrow())
            }
        }
    }


    override suspend fun readTeacher(id: String): Result<TeacherReadModel> {
        return with(handler) {
            withExceptionHandle {
                val json = ApiFactory.academicApi().readTeacherById(id)
                if (json._isTeacherEntity()) {
                    val entity = json.parseOrThrow(TeacherReadEntity.serializer())
                    return Result.success(
                        with(ReadEntityModelMapper) { entity.toModel() }
                    )
                } else
                    return Result.failure(json.parseAsServerMessageOrThrow())
            }
        }
    }

    override suspend fun insertTeacher(model: TeacherWriteModel): Result<Unit> {
        return with(handler) {
            withExceptionHandle {
                val entity = with(ModelMapper) { model.toEntity() }
                val dataJson = jsonConverter.encodeToString(entity)
                val responseJson = ApiFactory.academicApi().insertTeacher(model.deptId, dataJson)

                return Result.failure(
                    responseJson.parseAsServerMessageOrThrow()
                )
            }
        }
    }


    override suspend fun getAllDept(): Result<List<DepartmentReadModel>> {
        return with(handler) {
            withExceptionHandle {
                val json = ApiFactory.academicApi().readAllDept()

                if (json._isDepartmentListEntity()) {
                    val entities =
                        json.parseOrThrow(ListSerializer(DepartmentReadEntity.serializer()))
                    return Result.success(
                        with(ReadEntityModelMapper) {
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
                val entity = with(ModelMapper) { model.toEntity() }
                val dataJson = jsonConverter.encodeToString(entity)
                val responseJson = ApiFactory.academicApi().updateFaculty(
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
                val entity = with(ModelMapper) { model.toEntity() }
                val dataJson = jsonConverter.encodeToString(entity)
                val responseJson = ApiFactory.academicApi().updateDept(
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
                val entity = with(ModelMapper) { model.toEntity() }
                val dataJson = jsonConverter.encodeToString(entity)
                val responseJson = ApiFactory.academicApi().updateTeacher(
                    teacherId = teacherId,
                    json = dataJson
                )
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