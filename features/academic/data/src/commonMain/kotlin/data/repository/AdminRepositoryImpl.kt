@file:Suppress("functionName")

package data.repository

import core.database.api.ApiFactory
import core.network.JsonParser
import data.ModelMapper
import data.entity.admin.DepartmentEntryEntity
import data.entity.admin.FacultyEntryEntity
import data.entity.public_.FacultyEntity
import data.service.JsonHandler
import data.service.withExceptionHandle
import faculty.domain.model.admin.DepartmentEntryModel
import faculty.domain.model.admin.FacultyEntryModel
import faculty.domain.model.admin.TeacherEntryModel
import faculty.domain.model.public_.DepartmentModel
import faculty.domain.model.public_.FacultyModel
import faculty.domain.repository.AdminRepository
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class AdminRepositoryImpl internal constructor(
    private val handler: JsonHandler,
    private val jsonParser: JsonParser,
) : AdminRepository {
    private val jsonConverter = Json { prettyPrint = true }
    override suspend fun addFaculty(model: FacultyEntryModel): Result<Unit> {
        return with(handler) {
            withExceptionHandle {
                val entity = with(ModelMapper) { model.toEntity() }
                val dataJson = jsonConverter.encodeToString(entity)

                /** Execution is here means server sent a response we have to parse it
                 * - 2 possible cases: We got excepted  json or Json is a server message in format ServerResponseMessageEntity  or  Server send a json that format is not known yet,may be server change it json format or other
                 */
                val responseJson = ApiFactory.academicAdminApi().insertFaculty(dataJson)
                return Result.failure(responseJson.parseAsServerMessageOrThrow())
            }
        }
    }

    override suspend fun readFaculty(id: String): Result<FacultyEntryModel> {
        return with(handler) {
            withExceptionHandle {
                val json = ApiFactory.academicAdminApi().getDepartments()
                if (json._isFacultyEntity()) {
                    val entity = json.parseOrThrow(FacultyEntryEntity.serializer())
                    return Result.success(
                        with(ModelMapper){ entity.toEntryModel() }
                    )
                } else
                    return Result.failure(json.parseAsServerMessageOrThrow())

            }
        }
    }

    override suspend fun updateFaculty(model: FacultyEntryModel): Result<Unit> {
        TODO()
    }

    override suspend fun addDepartment(model: DepartmentEntryModel): Result<Unit> {
        return with(handler) {
            withExceptionHandle {
                val entity = with(ModelMapper) { model.toEntity() }
                val dataJson = jsonConverter.encodeToString(entity)
                val responseJson = ApiFactory.academicAdminApi().insertDept(model.facultyId,dataJson)
                return Result.failure(responseJson.parseAsServerMessageOrThrow())
            }
        }
    }

    override suspend fun updateDepartment(model: DepartmentEntryModel): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun addTeacher(model: TeacherEntryModel): Result<Unit> {
        return with(handler) {
            withExceptionHandle {
                val entity = with(ModelMapper) { model.toEntity() }
                val dataJson = jsonConverter.encodeToString(entity)
                val responseJson = ApiFactory.academicAdminApi().insertTeacher(model.deptId,dataJson)

                return Result.failure(
                    responseJson.parseAsServerMessageOrThrow()
                )
            }
        }
    }


    override suspend fun updateTeacher(model: TeacherEntryModel): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllDept(): Result<List<DepartmentModel>> {
        return with(handler) {
            withExceptionHandle {
                val json = ApiFactory.academicAdminApi().getDepartments()

                if (json._isDepartmentListEntity()) {
                    val entities = json.parseOrThrow(ListSerializer(DepartmentEntryEntity.serializer()))
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

    private fun String._isFacultyEntity() =
        jsonParser.parse(this, FacultyEntryEntity.serializer()).isSuccess
    private fun String._isDepartmentListEntity() =
        jsonParser.parse(this, ListSerializer(DepartmentEntryEntity.serializer())).isSuccess
}