package faculty.data.department.repoisitory

import common.di.AuthTokenFactory
import database.local.api.AcademicAPIs
import database.local.schema.DepartmentEntityLocal
import core.network.netManagerProvider
import faculty.data.PackageLevelAccess
import faculty.data.department.data_sources.remote.DepartmentListRemoteDataSource
import faculty.data.department.data_sources.remote.entity.DepartmentListEntity
import faculty.domain.department.model.DepartmentListModel
import faculty.domain.department.repoisitory.DepartmentListRepository

@OptIn(PackageLevelAccess::class)
class DepartmentListRepositoryImpl : DepartmentListRepository {
    override suspend fun getDepartment(facultyId: String): Result<List<DepartmentListModel>> {
        if (!netManagerProvider().isInternetAvailable()) {
            val localData = AcademicAPIs.retrieveDepartments(facultyId).getOrDefault(emptyList())
                .map { it.toModel() }
                .sortedBy { it.id }
            println(localData)
            return Result.success(localData)

        }
        val token = AuthTokenFactory.retrieveToken().getOrNull()

        val response: Result<DepartmentListEntity> = DepartmentListRemoteDataSource(
            token = token,
            facultyId = facultyId
        ).getDepartments()
        return if (response.isSuccess)
            onSuccess(facultyId, response.getOrNull())
        else
            onFailure(response.exceptionOrNull())
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
            addToLocalDatabase(facultyId, result)
            Result.success(result)

        }

    }

    private fun onFailure(exception: Throwable?): Result<List<DepartmentListModel>> {
        val ex = exception ?: Throwable("Reason is null at ,DepartmentListRepositoryImpl")
        return Result.failure(ex)
    }

    private suspend fun addToLocalDatabase(facultyId: String, entities: List<DepartmentListModel>) {
        val models = entities.map {
            DepartmentEntityLocal(
                id = it.id,
                deptId = it.deptId,
                employeeCount = it.employeeCount,
                name = it.name,
                shortname = it.shortName
            )
        }
        AcademicAPIs.addDepartments(facultyId, models)
    }

}

private fun DepartmentEntityLocal.toModel() = DepartmentListModel(
    id = this.id,
    deptId = this.deptId,
    name = name,
    shortName = shortname,
    employeeCount = employeeCount

)