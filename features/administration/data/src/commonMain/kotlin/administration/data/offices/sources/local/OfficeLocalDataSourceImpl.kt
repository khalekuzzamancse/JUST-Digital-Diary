package administration.data.offices.sources.local

import database.local.api.AdministrationAPIs
import database.local.schema.administration.OfficeEntityLocal

class OfficeLocalDataSourceImpl: OfficeLocalDataSource {
    override suspend fun getOffices(): Result<List<OfficeEntityLocal>> {
       return AdministrationAPIs.retrieveAdminOffices()
    }

    override suspend fun addOffices(entities: List<OfficeEntityLocal>) {
        AdministrationAPIs.addAdminOffices(entities)
    }
}