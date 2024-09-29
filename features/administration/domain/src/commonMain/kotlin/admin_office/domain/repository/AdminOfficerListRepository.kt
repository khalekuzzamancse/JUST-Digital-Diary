package admin_office.domain.repository

import admin_office.domain.model.AdminOfficerModel

interface AdminOfficerListRepository{

    suspend fun getOfficers(subOfficeId:String): Result<List<AdminOfficerModel>>

}