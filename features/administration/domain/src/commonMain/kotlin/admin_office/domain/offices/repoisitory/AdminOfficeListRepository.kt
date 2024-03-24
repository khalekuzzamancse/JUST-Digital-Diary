package admin_office.domain.offices.repoisitory

import admin_office.domain.offices.model.AdminOfficeModel

interface AdminOfficeListRepository{

    suspend fun getAdminOffices(): Result<List<AdminOfficeModel>>

}