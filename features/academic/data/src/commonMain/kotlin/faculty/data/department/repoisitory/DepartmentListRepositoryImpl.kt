package faculty.data.department.repoisitory

import common.di.AuthTokenFactory
import faculty.data.PackageLevelAccess
import faculty.data.department.data_sources.remote.DepartmentListRemoteDataSource
import faculty.data.department.data_sources.remote.entity.DepartmentListEntity
import faculty.domain.department.model.DepartmentListModel
import faculty.domain.department.repoisitory.DepartmentListRepository

@OptIn(PackageLevelAccess::class)
class DepartmentListRepositoryImpl : DepartmentListRepository {
    override suspend fun getDepartment(facultyId: String): Result<List<DepartmentListModel>> {
        val token= AuthTokenFactory.retrieveToken().getOrNull()

        val response: Result<DepartmentListEntity> = DepartmentListRemoteDataSource(
            token = token,
            facultyId = facultyId
        ).getDepartments()
        return if (response.isSuccess)
            onSuccess(response.getOrNull())
        else
            onFailure(response.exceptionOrNull())
    }

    private fun onSuccess(entity: DepartmentListEntity?): Result<List<DepartmentListModel>> {
        return if (entity == null)
            Result.failure(Throwable("Success but dept list is NULL at , DepartmentListRepositoryImpl:onSuccess()"))
        else
            Result.success(
                entity.departments.map {
                    DepartmentListModel(
                        id = it.dept_id,
                        name = it.name,
                        shortName = it.shortname,
                    )
                }
            )
    }

    private fun onFailure(exception: Throwable?): Result<List<DepartmentListModel>> {
        val ex = exception ?: Throwable("Reason is null at ,DepartmentListRepositoryImpl")
        return Result.failure(ex)
    }
}