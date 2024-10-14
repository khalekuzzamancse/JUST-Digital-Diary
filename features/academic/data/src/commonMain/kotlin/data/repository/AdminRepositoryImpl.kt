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
import faculty.domain.model.admin.DepartmentEntryModel
import faculty.domain.model.admin.FacultyEntryModel
import faculty.domain.model.admin.TeacherEntryModel
import faculty.domain.model.public_.DepartmentModel
import faculty.domain.repository.AdminRepository
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class AdminRepositoryImpl internal constructor(
    private val handler: JsonHandler,
    private val jsonParser: JsonParser,
) : AdminRepository {
    private val jsonConverter = Json { prettyPrint = true }
    override suspend fun insertFaculty(model: FacultyEntryModel): Result<Unit> {
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

    override suspend fun readFaculty(id: String): Result<FacultyEntryModel> {
        return with(handler) {
            withExceptionHandle {
                val json = ApiFactory.academicApi().readFacultyById(id)
                if (json._isFacultyEntity()) {
                    val entity = json.parseOrThrow(FacultyEntryEntity.serializer())
                    return Result.success(
                        with(ModelMapper) { entity.toEntryModel() }
                    )
                } else
                    return Result.failure(json.parseAsServerMessageOrThrow())
            }
        }
    }


    override suspend fun readDept(id: String): Result<DepartmentEntryModel> {
        return with(handler) {
            withExceptionHandle {
                val json = ApiFactory.academicApi().readDeptById(id)
                if (json._isDeptEntity()) {
                    val entity = json.parseOrThrow(DepartmentEntryEntity.serializer())
                    return Result.success(
                        with(ModelMapper) { entity.toEntryModel() }
                    )
                } else
                    return Result.failure(json.parseAsServerMessageOrThrow())

            }
        }
    }

    override suspend fun insertDept(model: DepartmentEntryModel): Result<Unit> {
        return with(handler) {
            withExceptionHandle {
                val entity = with(ModelMapper) { model.toEntity() }
                val dataJson = jsonConverter.encodeToString(entity)
                val responseJson = ApiFactory.academicApi().insertDept(model.facultyId, dataJson)
                return Result.failure(responseJson.parseAsServerMessageOrThrow())
            }
        }
    }


    override suspend fun readTeacher(id: String): Result<TeacherEntryModel> {
        return with(handler) {
            withExceptionHandle {
                val json = ApiFactory.academicApi().readTeacherById(id)
                if (json._isTeacherEntity()) {
                    val entity = json.parseOrThrow(TeacherEntryEntity.serializer())
                    return Result.success(
                        with(ModelMapper) { entity.toEntryModel() }
                    )
                } else
                    return Result.failure(json.parseAsServerMessageOrThrow())
            }
        }
    }

    override suspend fun insertTeacher(model: TeacherEntryModel): Result<Unit> {
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


    override suspend fun getAllDept(): Result<List<DepartmentModel>> {
        return with(handler) {
            withExceptionHandle {
                val json = ApiFactory.academicApi().readAllDept()

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

    //TODO:UPDATE OPERATIONS---------UPDATE OPERATIONS
    //TODO:UPDATE OPERATIONS---------UPDATE OPERATIONS
    //TODO:UPDATE OPERATIONS---------UPDATE OPERATIONS
    override suspend fun updateFaculty(facultyId: String, model: FacultyEntryModel): Result<Unit> {
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
        model: DepartmentEntryModel
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

    override suspend fun updateTeacher(teacherId: String, model: TeacherEntryModel): Result<Unit> {
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
        jsonParser.parse(this, FacultyEntryEntity.serializer()).isSuccess

    private fun String._isDepartmentListEntity() =
        jsonParser.parse(this, ListSerializer(DepartmentEntryEntity.serializer())).isSuccess

    private fun String._isDeptEntity() =
        jsonParser.parse(this, DepartmentEntryEntity.serializer()).isSuccess

    private fun String._isTeacherEntity() =
        jsonParser.parse(this, TeacherEntryEntity.serializer()).isSuccess
}