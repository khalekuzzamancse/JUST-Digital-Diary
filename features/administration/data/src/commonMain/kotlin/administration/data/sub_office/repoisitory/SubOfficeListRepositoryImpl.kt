package administration.data.sub_office.repoisitory

import admin_office.domain.sub_offices.model.SubOfficeModel
import admin_office.domain.sub_offices.repoisitory.SubOfficeListRepository
import administration.data.DependencyFactory
import administration.data.PackageLevelAccess
import administration.data.sub_office.data_sources.remote.SubOfficeRemoteDataSource
import administration.data.sub_office.data_sources.remote.entity.SubOfficeEntity
import administration.data.sub_office.data_sources.remote.entity.SubOfficeListEntity
import common.di.AuthTokenFactory

import database.local.schema.administration.SubOfficeEntityLocal

class SubOfficeListRepositoryImpl : SubOfficeListRepository {
    private val localSource = DependencyFactory.subOfficeLocalDataSource()
    private lateinit var remoteSource: SubOfficeRemoteDataSource
    private lateinit var officeId: String

    override suspend fun getSubOffices(officeId: String): Result<List<SubOfficeModel>> {
        this.officeId = officeId
        remoteSource = DependencyFactory.subOfficeRemoteDataSource(officeId)
        val token = AuthTokenFactory.retrieveToken().getOrNull()
        return if (token == null) fetchFromLocalDatabase(officeId) else fetchFromRemoteDatabase(
            token,
            officeId
        )

    }

    @OptIn(PackageLevelAccess::class)
    private suspend fun fetchFromRemoteDatabase(
        token: String,
        officeId: String
    ): Result<List<SubOfficeModel>> {
        val response = remoteSource.getSubOffices(token, officeId)
        return if (response.isSuccess)
            onSuccess(response.getOrNull())
        else
            onFailure(response.exceptionOrNull())
    }

    private suspend fun fetchFromLocalDatabase(officeId: String): Result<List<SubOfficeModel>> =
        localSource.getSubOffices(officeId).toResult()

    @OptIn(PackageLevelAccess::class) // Permits access to "SubOfficeListEntity" within  this parent package
    private suspend fun onSuccess(entity: SubOfficeListEntity?): Result<List<SubOfficeModel>> {
        return if (entity == null)
            Result.failure(Throwable("Success but dept list is NULL at , AdminSubOfficeListRepositoryImpl:onSuccess()"))
        else {
            val entities = entity.sub_offices.map(::toModel)
            localSource.addSubOffices(entities.mapIndexed { index, model -> model.toModel(index, officeId)})
            Result.success(entities)
        }

    }

    private fun onFailure(exception: Throwable?): Result<List<SubOfficeModel>> {
        val ex = exception ?: Throwable("Reason is null at ,AdminSubOfficeListRepositoryImpl")
        return Result.failure(ex)
    }


}

private fun Result<List<SubOfficeEntityLocal>>.toResult(): Result<List<SubOfficeModel>> {
    return if (this.isSuccess)
        Result.success(this.getOrDefault(emptyList()).map(::toModel))
    else
        Result.failure(
            exception = this.exceptionOrNull()
                ?: Throwable("Unknown error at :AdminOfficeListRepositoryImpl")
        )
}

@OptIn(PackageLevelAccess::class)
private fun toModel(entity: SubOfficeEntity) = SubOfficeModel(
    name = entity.name,
    officeId = entity.sub_office_id,
    employeeCount = entity.office_members_count
)

private fun toModel(entity: SubOfficeEntityLocal) = SubOfficeModel(
    name = entity.name,
    officeId = entity.officeId,
    employeeCount = entity.officeMembersCount
)

private fun SubOfficeModel.toModel(serialNo: Int, officeId: String) = SubOfficeEntityLocal(
    name = this.name,
    serialNo = serialNo,
    officeId = officeId,
    officeMembersCount = this.employeeCount
)

