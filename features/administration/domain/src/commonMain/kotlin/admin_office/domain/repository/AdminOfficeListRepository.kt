package admin_office.domain.repository

import admin_office.domain.model.OfficeModel

interface AdminOfficeListRepository{

    suspend fun getAdminOffices(): Result<List<OfficeModel>>
}