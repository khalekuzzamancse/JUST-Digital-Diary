package administration.data.offices.repoisitory

import admin_office.domain.offices.model.OfficeModel
import admin_office.domain.offices.repoisitory.AdminOfficeListRepository
import administration.data.DependencyFactory
import administration.data.PackageLevelAccess
import administration.data.offices.sources.remote.entity.AdminOfficeInfoEntity
import administration.data.offices.sources.remote.entity.AdminOfficeListEntity
import common.di.AuthTokenFactory
import database.local.schema.administration.OfficeEntityLocal

/**
 * Instead of passing via constructor ,using factory to hiding some classes from client
 */
class AdminOfficeListRepositoryImpl : AdminOfficeListRepository {
    private val localSource = DependencyFactory.officeLocalDataSource()
    private val remoteSource = DependencyFactory.officeRemoteDataSource()

    override suspend fun getAdminOffices(): Result<List<OfficeModel>> {
        val token = AuthTokenFactory.retrieveToken().getOrNull()
        return if (token == null) fetchFromLocalDatabase()
        else fetchFromRemoteDatabase(token)

    }

    @OptIn(PackageLevelAccess::class)
    private suspend fun fetchFromRemoteDatabase(token: String): Result<List<OfficeModel>> {
        val response = remoteSource.getOffices(token)
        return if (response.isSuccess)
            onSuccess(response.getOrNull())
        else
            onFailure(response.exceptionOrNull())
    }

    private suspend fun fetchFromLocalDatabase(): Result<List<OfficeModel>> =
        localSource.getOffices().toResult()

    @OptIn(PackageLevelAccess::class)
    private suspend fun onSuccess(entity: AdminOfficeListEntity?): Result<List<OfficeModel>> {
        return if (entity == null)
            Result.failure(Throwable("Success but dept list is NULL at , AdminOfficeListRepositoryImpl:onSuccess()"))
        else {
            val entities = entity.offices.map(::toModel)
            localSource.addOffices(entities.mapIndexed { index, model -> model.toLocalEntity(index) })
            Result.success(entities)
        }

    }

    private fun onFailure(exception: Throwable?): Result<List<OfficeModel>> {
        val ex = exception ?: Throwable("Reason is null at ,AdminOfficeListRepositoryImpl")
        return Result.failure(ex)
    }


}

private fun Result<List<OfficeEntityLocal>>.toResult(): Result<List<OfficeModel>> {
    return if (this.isSuccess)
        Result.success(this.getOrDefault(emptyList()).map(::toModel))
    else
        Result.failure(
            exception = this.exceptionOrNull()
                ?: Throwable("Unknown error at :AdminOfficeListRepositoryImpl")
        )
}

@OptIn(PackageLevelAccess::class)
private fun toModel(entity: AdminOfficeInfoEntity) = OfficeModel(
    name = entity.name,
    officeId = entity.office_id,
    subOfficeCount = entity.sub_offices_count,
)

private fun toModel(entity: OfficeEntityLocal) = OfficeModel(
    name = entity.name,
    officeId = entity.name,
    subOfficeCount = entity.subOfficesCount
)

private fun OfficeModel.toLocalEntity(id: Int) = OfficeEntityLocal(
    id = id,//TODO("Update later to order or sort the list)
    name = this.name,
    officeId = this.officeId,
    subOfficesCount = this.subOfficeCount
)