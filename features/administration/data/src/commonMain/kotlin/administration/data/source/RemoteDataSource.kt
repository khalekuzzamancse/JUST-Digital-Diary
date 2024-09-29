package administration.data.source

import admin_office.domain.model.AdminOfficerModel
import admin_office.domain.model.OfficeModel
import admin_office.domain.model.SubOfficeModel

interface RemoteDataSource {
    suspend fun getOffices(token: String): Result<List<OfficeModel>>
    suspend fun getSubOffices(token: String, officeId: String): Result<List<SubOfficeModel>>
     suspend fun getOfficers(subOfficeId: String, token: String): Result<List<AdminOfficerModel>>
}
