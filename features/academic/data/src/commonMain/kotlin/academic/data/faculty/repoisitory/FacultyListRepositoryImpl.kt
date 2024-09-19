package academic.data.faculty.repoisitory

import common.di.AuthTokenFactory

import academic.data.DependencyFactory
import academic.data.faculty.source.local.FacultyLocalDataSourceImpl
import academic.data.faculty.source.remote.entity.FacultyListResponseEntity
import faculty.domain.faculties.model.FacultyInfoModel
import faculty.domain.faculties.repoisitory.FacultyListRepository

/**
 * Instead of passing via constructor ,using factory to hiding some classes from client
 */
class FacultyListRepositoryImpl : FacultyListRepository {
    private val localSource= DependencyFactory.facultyLocalDataSource()
    private val remoteSource= DependencyFactory.facultyRemoteDataSource()
    override suspend fun getFaculties(): Result<List<FacultyInfoModel>> {
        val token = AuthTokenFactory.retrieveToken().getOrNull()
        return if (token == null) localSource.getFaculties()
        else {
            val response = remoteSource.getFaculties(token)
            if (response.isSuccess)
                onRemoteRequestSuccess(response.getOrNull())
            else onRemoteRequestFailure(response.exceptionOrNull())
        }

    }


    private suspend fun onRemoteRequestSuccess(entity: FacultyListResponseEntity?): Result<List<FacultyInfoModel>> {
        return if (entity == null)
            Result.failure(Throwable("Success but dept list is NULL at , FacultyListRepositoryImpl"))
        else {
            val entities = entity.faculty.map {
                FacultyInfoModel(
                    facultyId = it.faculty_id,
                    name = it.name,
                    departmentsCount = it.department_count,
                    id = it.id,
                )
            }
            addToLocalDatabase(entities)
            Result.success(entities)
        }

    }

    private fun onRemoteRequestFailure(exception: Throwable?): Result<List<FacultyInfoModel>> {
        val ex = exception ?: Throwable("Reason is null at ,FacultyListRepositoryImpl")
        return Result.failure(ex)
    }

    private suspend fun addToLocalDatabase(entities: List<FacultyInfoModel>) =
        FacultyLocalDataSourceImpl().addFaculties(entities)


}

