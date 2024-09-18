package administration.data.sub_office.data_sources.local

import database.local.schema.administration.SubOfficeEntityLocal

interface SubOfficeLocalDataSource {
    suspend fun getSubOffices(officeId:String):Result<List<SubOfficeEntityLocal>>
    suspend fun addSubOffices(entity:List<SubOfficeEntityLocal>)
}