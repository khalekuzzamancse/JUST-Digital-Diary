package admin_office.domain.repository

import admin_office.domain.model.AdminOfficerModel
import admin_office.domain.model.OfficeModel
import admin_office.domain.model.SubOfficeModel

interface Repository {
    suspend fun getAdminOffices(): Result<List<OfficeModel>>
    suspend fun getSubOffices(officeId:String): Result<List<SubOfficeModel>>
    suspend fun getOfficers(subOfficeId:String): Result<List<AdminOfficerModel>>
}