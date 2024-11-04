package core.database.factory

import core.data.entity.administration.AdminOfficerReadEntity
import core.data.entity.administration.OfficeReadEntity
import core.data.entity.administration.SubOfficeReadEntity
import core.database.api.AdministrationApiFacade
import domain.api.AdministrationApi

class AdministrationApiFacadeImpl(
    private val remoteApi:AdministrationApi?
): AdministrationApiFacade {
    override suspend fun readOfficesOrThrow(): List<OfficeReadEntity> {
        return remoteApi!!.readOfficesOrThrow()
    }

    override suspend fun readSubOfficesOrThrow(officeId: String): List<SubOfficeReadEntity> {
        return remoteApi!!.readSubOfficesOrThrow(officeId)
    }

    override suspend fun readEmployeesOrThrow(subOfficeId: String): List<AdminOfficerReadEntity> {
        return remoteApi!!.readEmployeesOrThrow(subOfficeId)
    }
}