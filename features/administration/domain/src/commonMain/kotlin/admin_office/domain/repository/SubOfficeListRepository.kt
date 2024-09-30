package admin_office.domain.repository

import admin_office.domain.model.SubOfficeModel

interface SubOfficeListRepository{

    suspend fun getSubOffices(officeId:String): Result<List<SubOfficeModel>>

}