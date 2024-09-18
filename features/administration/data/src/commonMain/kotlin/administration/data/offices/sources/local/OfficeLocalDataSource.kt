package administration.data.offices.sources.local

import database.local.schema.administration.OfficeEntityLocal

interface OfficeLocalDataSource {
    suspend fun getOffices(): Result<List<OfficeEntityLocal>>
    suspend fun addOffices(entities:List<OfficeEntityLocal>)

}