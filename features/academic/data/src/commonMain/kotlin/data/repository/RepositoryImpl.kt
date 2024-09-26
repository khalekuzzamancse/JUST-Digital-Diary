package data.repository

import core.network.ApiServiceClient
import core.network.JsonParser
import data.schema.DepartmentListEntity
import data.schema.FacultyListResponseEntity
import data.schema.TeacherListEntity
import faculty.domain.model.DepartmentModel
import faculty.domain.model.FacultyModel
import faculty.domain.model.TeacherModel
import faculty.domain.repository.Repository

/**
 * - This class receives all dependencies via the constructor, making it easy to integrate
 * with Dependency Injection (DI)
 * - It depends on  abstraction so via the `factory method` any time it dependencies can be altered with different
 * implementations
 *- Should not create instances of it via the constructor; instead,
 * use a factory method or dependency injection (DI) to obtain an instance
 */
class RepositoryImpl internal constructor(
    private val apiServiceClient: ApiServiceClient,
    private val jsonParser: JsonParser
) : Repository {

    override suspend fun getFaculties(token: String?): Result<List<FacultyModel>> {

        val classLoader = this::class.java.classLoader
        val json = classLoader?.getResource("faculty_list.json")?.readText()//resource/faculty_list.json
        if (json != null) {
            val result = json.let { jsonParser.parse(it, FacultyListResponseEntity.serializer()) }
            return result.fold(
                onSuccess = { entity ->
                    Result.success(entity._toFacultyModel())
                },
                onFailure = { exception ->
                    Result.failure(exception)
                }
            )
        }
        return Result.failure(Throwable("Json is full,failed to read the file"))


    }
    override suspend fun getDepartment(
        token: String?,
        facultyId: String
    ): Result<List<DepartmentModel>> {
        val classLoader = this::class.java.classLoader
        val json = classLoader?.getResource("department_list_demo_data.json")
            ?.readText()//resource/department_list_demo_data.json
        println(json)
        if (json != null) {
            val result = json.let { jsonParser.parse(it, DepartmentListEntity.serializer()) }
            return result.fold(
                onSuccess = { entity ->
                    println(entity)
                    Result.success(entity._toDeptModel())
                },
                onFailure = { exception ->
                    Result.failure(Throwable("Failed to parse json:$exception"))
                }
            )
        }
        return Result.failure(Throwable("Json is full,failed to read the file"))
    }

    private fun DepartmentListEntity._toDeptModel(): List<DepartmentModel> {
        return this.departments.map {
            DepartmentModel(
                deptId = it.deptId,
                name = it.name,
                shortName = it.shortName,
            )
        }


    }
    override suspend fun getTeachers(deptId: String): Result<List<TeacherModel>> {
        val classLoader = this::class.java.classLoader
        val json =
            classLoader?.getResource("teacher_list_demo_data.json")?.readText()//resource/teacher_list_demo_data.json
        if (json != null) {
            val result = json.let { jsonParser.parse(it, TeacherListEntity.serializer()) }
            return result.fold(
                onSuccess = { entity ->
                    Result.success(entity._toModel())
                },
                onFailure = { exception ->
                    Result.failure(exception)
                }
            )
        }
        return Result.failure(Throwable("Json is full,failed to read the file"))


    }
    private fun TeacherListEntity._toModel(): List<TeacherModel> {
        return this.facultyMembers.map { teacher ->
            TeacherModel(
                id = 0, //update later
                name = teacher.name,
                email = teacher.email,
                additionalEmail = teacher.additional_email ?: "",
                profileImageLink = teacher.profile_image ?: "",
                achievements = teacher.achievement,
                phone = teacher.phone ?: "",
                designations = teacher.designations.toString(),
                deptName = teacher.department_name,
                deptSortName = teacher.shortname,
                roomNo = teacher.room_no
            )
        }

    }


    private suspend fun FacultyListResponseEntity._toFacultyModel(): List<FacultyModel> {
        return this.faculty.map {
            FacultyModel(
                facultyId = it.facultyId,
                name = it.name,
                departmentsCount = it.departmentCount,
                id = it.id,
            )

            //  addToLocalDatabase(entities)


        }
    }



}

