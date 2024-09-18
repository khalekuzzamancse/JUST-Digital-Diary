package admin_office.domain.offices.repoisitory

import admin_office.domain.offices.model.OfficeModel

interface AdminOfficeListRepository{

    suspend fun getAdminOffices(): Result<List<OfficeModel>>

}