package administration.data.repository

import admin_office.domain.exception.CustomException
import admin_office.domain.model.AdminOfficerModel
import admin_office.domain.model.OfficeModel
import admin_office.domain.model.SubOfficeModel
import admin_office.domain.repository.Repository
import administration.data.source.RemoteDataSource

class RepositoryImpl internal  constructor(
    private val token:String?,
    private val remoteSource: RemoteDataSource
):Repository {
    override suspend fun getAdminOffices(): Result<List<OfficeModel>> {
        return if (token != null)
            remoteSource.getOffices(token)
        else
            Result.failure(CustomException.UnKnownException(Throwable("Token is null")))
    }

    override suspend fun getSubOffices(officeId: String): Result<List<SubOfficeModel>> {
        return if (token != null)
            remoteSource.getSubOffices(token=token, officeId=officeId)
        else
            Result.failure(CustomException.UnKnownException(Throwable("Token is null")))
    }

    override suspend fun getOfficers(subOfficeId: String): Result<List<AdminOfficerModel>> {
        return if (token != null)
            remoteSource.getOfficers(subOfficeId = subOfficeId,token=token)
        else
            Result.failure(CustomException.UnKnownException(Throwable("Token is null")))
    }
}