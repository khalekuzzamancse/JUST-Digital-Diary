package administration.data.sub_office.repoisitory

import admin_office.domain.sub_offices.model.AdminSubOfficeModel
import admin_office.domain.sub_offices.repoisitory.AdminSubOfficeListRepository
import administration.data.sub_office.data_sources.remote.AdminSubOfficeListRemoteDataSource
import administration.data.sub_office.data_sources.remote.entity.SubOfficeListEntity

class AdminSubOfficeListRepositoryImpl(
    private val token: String?
) : AdminSubOfficeListRepository {


    override suspend fun getSubOffices(officeId: String): Result<List<AdminSubOfficeModel>> {
        val response = AdminSubOfficeListRemoteDataSource(
            officeId = officeId,
            token = token,
        ).getSubOffices()
        return if (response.isSuccess)
            onSuccess(response.getOrNull())
        else
            onFailure(response.exceptionOrNull())
    }


    private fun onSuccess(entity: SubOfficeListEntity?): Result<List<AdminSubOfficeModel>> {
        return if (entity == null)
            Result.failure(Throwable("Success but dept list is NULL at , AdminSubOfficeListRepositoryImpl:onSuccess()"))
        else
            Result.success(
                entity.sub_offices.map {
                    AdminSubOfficeModel(
                        name = it.name,
                        id = it.sub_office_id,
                        employeeCount = it.office_members_count,
                    )
                }
            )
    }

    private fun onFailure(exception: Throwable?): Result<List<AdminSubOfficeModel>> {
        val ex = exception ?: Throwable("Reason is null at ,AdminSubOfficeListRepositoryImpl")
        return Result.failure(ex)
    }



}