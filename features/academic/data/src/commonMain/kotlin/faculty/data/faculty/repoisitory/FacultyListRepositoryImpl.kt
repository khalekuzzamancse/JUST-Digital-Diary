package faculty.data.faculty.repoisitory

import common.di.AuthTokenFactory
import core.network.netManagerProvider
import database.local.api.AcademicAPIs
import database.local.schema.FacultyEntityLocal
import faculty.data.faculty.data_sources.remote.FacultyListRemoteDataSource
import faculty.data.faculty.data_sources.remote.entity.FacultyListResponseEntity
import faculty.domain.faculties.model.FacultyInfoModel
import faculty.domain.faculties.repoisitory.FacultyListRepository

class FacultyListRepositoryImpl : FacultyListRepository {

    override suspend fun getFaculties(): Result<List<FacultyInfoModel>> {
        if (!netManagerProvider().isInternetAvailable()) {
            val localData = AcademicAPIs
                .retrieveFaculties()
                .getOrDefault(emptyList()).map { it.toModel() }
                .sortedBy { it.id }
            println(localData)
            return Result.success(localData)

        }
        val token = AuthTokenFactory.retrieveToken().getOrNull()
        val response = FacultyListRemoteDataSource(token).getFaculties()
        return if (response.isSuccess)
            onSuccess(response.getOrNull())
        else onFailure(response.exceptionOrNull())
    }

    private suspend fun onSuccess(entity: FacultyListResponseEntity?): Result<List<FacultyInfoModel>> {
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

    private fun onFailure(exception: Throwable?): Result<List<FacultyInfoModel>> {
        val ex = exception ?: Throwable("Reason is null at ,FacultyListRepositoryImpl")
        return Result.failure(ex)
    }

    private suspend fun addToLocalDatabase(entities: List<FacultyInfoModel>) {
       val  models= entities.map {
           FacultyEntityLocal(
                id = it.id,
                facultyId = it.facultyId,
                name = it.name,
                deptCount = it.departmentsCount,
            )
        }
        AcademicAPIs.addFaculties(models)
    }


}

private fun FacultyEntityLocal.toModel() = FacultyInfoModel(
    id = id,
    name = name,
    facultyId = facultyId,
    departmentsCount = deptCount
)