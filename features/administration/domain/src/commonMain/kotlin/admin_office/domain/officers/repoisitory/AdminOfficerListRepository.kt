package admin_office.domain.officers.repoisitory

import admin_office.domain.officers.model.AdminOfficerModel

interface AdminOfficerListRepository{

    suspend fun getOfficers(subOfficeId:String): Result<List<AdminOfficerModel>>

}