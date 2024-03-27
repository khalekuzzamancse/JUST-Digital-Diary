package admin_office.domain.sub_offices.repoisitory

import admin_office.domain.sub_offices.model.SubOfficeModel

interface SubOfficeListRepository{

    suspend fun getSubOffices(officeId:String): Result<List<SubOfficeModel>>

}