package faculty.data.faculty.repoisitory

import faculty.data.faculty.data_sources.remote.FacultyListRemoteDataSource
import faculty.data.faculty.data_sources.remote.entity.FacultyListResponseEntity
import faculty.domain.faculties.model.FacultyInfoModel
import faculty.domain.faculties.repoisitory.FacultyListRepository

class FacultyListRepositoryImpl(private val token: String?) : FacultyListRepository {

    override suspend fun getFaculties(): Result<List<FacultyInfoModel>> {
        val response = FacultyListRemoteDataSource(token).getFaculties()
        return if (response.isSuccess)
            onSuccess(response.getOrNull())
        else onFailure(response.exceptionOrNull())
    }

    private fun onSuccess(entity: FacultyListResponseEntity?): Result<List<FacultyInfoModel>> {
        return if (entity == null)
            Result.failure(Throwable("Success but dept list is NULL at , FacultyListRepositoryImpl"))
        else
            Result.success(
                entity.faculty.map {
                    FacultyInfoModel(
                        facultyId = it.faculty_id,
                        name =  it.name,
                        departmentsCount =  it.department_count,
                        id =  it.id,
                    )
                }
            )
    }

    private fun onFailure(exception: Throwable?): Result<List<FacultyInfoModel>> {
        val ex = exception ?: Throwable("Reason is null at ,FacultyListRepositoryImpl")
        return Result.failure(ex)
    }
}