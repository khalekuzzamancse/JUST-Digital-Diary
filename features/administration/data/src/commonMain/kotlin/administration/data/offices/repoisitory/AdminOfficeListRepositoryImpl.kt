package administration.data.offices.repoisitory

import admin_office.domain.offices.model.AdminOfficeModel
import admin_office.domain.offices.repoisitory.AdminOfficeListRepository
import administration.data.PackageLevelAccess
import administration.data.offices.data_sources.remote.AdminOfficeListRemoteDataSource
import administration.data.offices.data_sources.remote.entity.AdminOfficeListEntity
import common.di.AuthTokenFactory

class AdminOfficeListRepositoryImpl : AdminOfficeListRepository {

    @OptIn(PackageLevelAccess::class)
    override suspend fun getAdminOffices(): Result<List<AdminOfficeModel>> {
        val token= AuthTokenFactory.retrieveToken().getOrNull()
        val response = AdminOfficeListRemoteDataSource(
            token = token,
        ).getOffices()
        return if (response.isSuccess)
            onSuccess(response.getOrNull())
        else
            onFailure(response.exceptionOrNull())
    }
    @OptIn(PackageLevelAccess::class)
    private fun onSuccess(entity: AdminOfficeListEntity?): Result<List<AdminOfficeModel>> {
        return if (entity == null)
            Result.failure(Throwable("Success but dept list is NULL at , AdminOfficeListRepositoryImpl:onSuccess()"))
        else
            Result.success(
                entity.offices.map {
                    AdminOfficeModel(
                        name = it.name,
                        id = it.office_id,
                        subOfficeCnt = it.sub_offices_count,
                    )
                }
            )
    }

    private fun onFailure(exception: Throwable?): Result<List<AdminOfficeModel>> {
        val ex = exception ?: Throwable("Reason is null at ,AdminOfficeListRepositoryImpl")
        return Result.failure(ex)
    }


}