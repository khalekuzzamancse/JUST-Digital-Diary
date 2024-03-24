package admin_office.domain.sub_offices.repoisitory

import admin_office.domain.sub_offices.model.AdminSubOfficeModel

interface AdminSubOfficeListRepository{

    suspend fun getSubOffices(officeId:String): Result<List<AdminSubOfficeModel>>

}