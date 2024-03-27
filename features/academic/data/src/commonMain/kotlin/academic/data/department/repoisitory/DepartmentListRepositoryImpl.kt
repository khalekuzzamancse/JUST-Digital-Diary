package academic.data.department.repoisitory

import common.di.AuthTokenFactory
import core.network.netManagerProvider
import academic.data.DependencyFactory
import academic.data.PackageLevelAccess
import academic.data.department.source.remote.entity.DepartmentListEntity
import faculty.domain.department.model.DepartmentListModel
import faculty.domain.department.repoisitory.DepartmentListRepository

/**
 * Instead of passing via constructor ,using factory to hiding some classes from client
 */
@OptIn(PackageLevelAccess::class)
class DepartmentListRepositoryImpl : DepartmentListRepository {
    private val localSource = DependencyFactory.departmentLocalDataSource()
    override suspend fun getDepartment(facultyId: String): Result<List<DepartmentListModel>> {
        val remoteSource = DependencyFactory.departmentRemoteDataSource(facultyId)
        if (!netManagerProvider().isInternetAvailable()) localSource.getDepartments(facultyId)
        val token = AuthTokenFactory.retrieveToken().getOrNull()
        return if (token == null) localSource.getDepartments(facultyId)
        else {
            val response = remoteSource.getDepartments(token)
            return if (response.isSuccess)
                onSuccess(facultyId, response.getOrNull())
            else
                onFailure(response.exceptionOrNull())
        }
    }

    private suspend fun onSuccess(
        facultyId: String,
        entity: DepartmentListEntity?
    ): Result<List<DepartmentListModel>> {
        return if (entity == null)
            Result.failure(Throwable("Success but dept list is NULL at , DepartmentListRepositoryImpl:onSuccess()"))
        else {
            val result = entity.departments.map {
                DepartmentListModel(
                    deptId = it.dept_id,
                    name = it.name,
                    shortName = it.shortname,
                )
            }
            localSource.addDepartments(facultyId, result)
            Result.success(result)

        }

    }

    private fun onFailure(exception: Throwable?): Result<List<DepartmentListModel>> {
        val ex = exception ?: Throwable("Reason is null at ,DepartmentListRepositoryImpl")
        return Result.failure(ex)
    }


}

