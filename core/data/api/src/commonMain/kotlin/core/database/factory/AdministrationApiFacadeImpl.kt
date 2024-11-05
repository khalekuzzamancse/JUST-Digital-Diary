package core.database.factory

import core.data.entity.administration.AdminOfficerReadEntity
import core.data.entity.administration.OfficeReadEntity
import core.data.entity.administration.SubOfficeReadEntity
import core.database.api.AdministrationApiFacade
import core.database.api.RemoteCacheHelper.fetchFromRemoteOrCache
import domain.api.AdministrationCacheApi
import domain.api.AdministrationRemoteApi

class AdministrationApiFacadeImpl(
    private val remoteApi:AdministrationRemoteApi?,
    private val cacheApi: AdministrationCacheApi
): AdministrationApiFacade {
    override suspend fun readOfficesOrThrow(): List<OfficeReadEntity> {
        return fetchFromRemoteOrCache(
            remoteApi = remoteApi,
            remoteCall = { remoteApi!!.readOfficesOrThrow() },
            cacheCall = { cacheApi.readOfficesOrThrow() },
            cacheUpdate = { cacheApi.insertOfficesOrThrow(it) }
        )
    }

    override suspend fun readSubOfficesOrThrow(officeId: String): List<SubOfficeReadEntity> {
        return fetchFromRemoteOrCache(
            remoteApi = remoteApi,
            remoteCall = { remoteApi!!.readSubOfficesOrThrow(officeId) },
            cacheCall = { cacheApi.readSubOfficesOrThrow(officeId) },
            cacheUpdate = { cacheApi.insertSubOfficesOrThrow(officeId,it) }
        )
    }

    override suspend fun readEmployeesOrThrow(subOfficeId: String): List<AdminOfficerReadEntity> {
        return fetchFromRemoteOrCache(
            remoteApi = remoteApi,
            remoteCall = { remoteApi!!.readEmployeesOrThrow(subOfficeId) },
            cacheCall = { cacheApi.readEmployeeOrThrow(subOfficeId) },
            cacheUpdate = { cacheApi.insertEmployeeOrThrow(subOfficeId,it) }
        )
    }
}