package administration.data.offices.repoisitory

import admin_office.domain.offices.model.AdminOfficeModel
import admin_office.domain.offices.repoisitory.AdminOfficeListRepository
import administration.data.offices.data_sources.remote.AdminOfficeListRemoteDataSource
import administration.data.offices.data_sources.remote.entity.AdminOfficeListEntity

class AdminOfficeListRepositoryImpl(
    private val token: String?
) : AdminOfficeListRepository {

    override suspend fun getAdminOffices(): Result<List<AdminOfficeModel>> {
        val response = AdminOfficeListRemoteDataSource(
            token = token,
        ).getOffices()
        return if (response.isSuccess)
            onSuccess(response.getOrNull())
        else
            onFailure(response.exceptionOrNull())
    }
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