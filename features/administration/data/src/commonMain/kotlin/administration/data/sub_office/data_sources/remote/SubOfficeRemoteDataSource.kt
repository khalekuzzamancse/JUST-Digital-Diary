package administration.data.sub_office.data_sources.remote

import administration.data.PackageLevelAccess
import administration.data.entity.SubOfficeListEntity

interface SubOfficeRemoteDataSource {
    @OptIn(PackageLevelAccess::class)
    suspend fun getSubOffices(token:String,officeId:String): Result<SubOfficeListEntity>
}