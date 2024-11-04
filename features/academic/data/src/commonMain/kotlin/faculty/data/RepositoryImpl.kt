@file:Suppress("functionName")

package faculty.data

import core.database.factory.ApiFactory
import faculty.domain.model.DepartmentReadModel
import faculty.domain.model.FacultyReadModel
import faculty.domain.model.TeacherReadModel
import faculty.domain.repository.Repository

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
class RepositoryImpl internal constructor(token: String?) : Repository {
    private val api = ApiFactory.academicApi(token)
    override suspend fun getFaculties(): Result<List<FacultyReadModel>> {
        return try {
            Result.success(
                api.readFacultiesOrThrow().map { EntityModelMapper.toModel(it) }
            )
        } catch (e: Exception) {
            Result.failure(e)
        }

    }

    override suspend fun getTeachers(deptId: String): Result<List<TeacherReadModel>> {
        return try {
            Result.success(api.readTeachersOrThrow(deptId).map { EntityModelMapper.toModel(it) })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getDepartment(facultyId: String): Result<List<DepartmentReadModel>> {
        return try {
            Result.success(api.readDeptsOrThrow(facultyId).map { EntityModelMapper.toModel(it) })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}
