package administration.data.sub_office.data_sources.local

//import database.local.api.AdministrationAPIs
//import database.local.schema.administration.SubOfficeEntityLocal
//
//class SubOfficeLocalDataSourceImpl:SubOfficeLocalDataSource {
//    override suspend fun getSubOffices(officeId: String): Result<List<SubOfficeEntityLocal>> {
//        return AdministrationAPIs.retrieveSubOffices(officeId)
//    }
//
//    override suspend fun addSubOffices(entity: List<SubOfficeEntityLocal>) {
//        println("addSubOffices():$entity")
//       AdministrationAPIs.addSubOffices(entity)
//    }
//}