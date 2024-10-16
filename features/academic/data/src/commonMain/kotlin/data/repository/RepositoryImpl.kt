package data.repository

import core.customexception.CustomException
import data.source.RemoteDataSource
import faculty.domain.model.public_.DepartmentModel
import faculty.domain.model.public_.FacultyModel
import faculty.domain.model.public_.TeacherModel
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
   private val  token: String?,
    private val remoteSource: RemoteDataSource
) : Repository {

    override suspend fun getFaculties(): Result<List<FacultyModel>> {
        return if (token != null)
            remoteSource.getFaculties(token)
        else
            Result.failure(CustomException.UnKnownException(Throwable("Token is null")))
    }

    override suspend fun getDepartment(
        facultyId: String
    ): Result<List<DepartmentModel>> {
        return if (token != null)
            remoteSource.getDepartment(facultyId = facultyId, token = token)
        else
            Result.failure(CustomException.UnKnownException(Throwable("Token is null")))
    }

    override suspend fun getTeachers(deptId: String): Result<List<TeacherModel>> {
        return if (token != null)
            remoteSource.getTeachers(token = token, deptId = deptId)
        else
            Result.failure(CustomException.UnKnownException(Throwable("Token is null")))
    }


}
