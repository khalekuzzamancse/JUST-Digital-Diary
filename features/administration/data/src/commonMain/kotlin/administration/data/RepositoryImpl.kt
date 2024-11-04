package administration.data

import admin_office.domain.model.AdminOfficerModel
import admin_office.domain.model.OfficeModel
import admin_office.domain.model.SubOfficeModel
import admin_office.domain.repository.Repository
import core.database.factory.ApiFactory

class RepositoryImpl internal  constructor(token: String?):Repository {
    private val apis= ApiFactory.administrationApi(token)
    override suspend fun getAdminOffices(): Result<List<OfficeModel>> {
       return try {
            return Result.success(Mapper.officeEntityToModel(apis.readOfficesOrThrow()))
        }
        catch (e:Exception){
            Result.failure(e)
        }


    }

    override suspend fun getSubOffices(officeId: String): Result<List<SubOfficeModel>> {
        return try {
            return Result.success(Mapper.subOfficeEntityToModel(apis.readSubOfficesOrThrow(officeId)))
        }
        catch (e:Exception){
            Result.failure(e)
        }


    }

    override suspend fun getOfficers(subOfficeId: String): Result<List<AdminOfficerModel>> {
        return try {
            return Result.success(Mapper.adminOfficersEntityToModel(apis.readEmployeesOrThrow(subOfficeId)))
        }
        catch (e:Exception){
            Result.failure(e)
        }

    }
}