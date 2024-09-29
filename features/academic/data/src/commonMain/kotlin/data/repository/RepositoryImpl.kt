package data.repository

import core.network.ApiServiceClient
import core.network.JsonParser
import data.entity.DepartmentListEntity
import data.entity.TeacherListEntity
import data.source.RemoteDataSource
import faculty.domain.exception.CustomException
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
    private val jsonParser: JsonParser,
    private val remoteSource: RemoteDataSource

) : Repository {

    override suspend fun getFaculties(token: String?): Result<List<FacultyModel>> {
        return if (token != null)
            remoteSource.getFaculties(token)
        else
            Result.failure(CustomException.UnKnownException(Throwable("Token is null")))
    }

    override suspend fun getDepartment(
        token: String?,
        facultyId: String
    ): Result<List<DepartmentModel>> {
        return if (token != null)
            remoteSource.getDepartment(token = token, facultyId = facultyId)
        else
            Result.failure(CustomException.UnKnownException(Throwable("Token is null")))
    }

    override suspend fun getTeachers(deptId: String, token: String?): Result<List<TeacherModel>> {
        return if (token != null)
            remoteSource.getTeachers(token = token, deptId = deptId)
        else
            Result.failure(CustomException.UnKnownException(Throwable("Token is null")))
    }


}

