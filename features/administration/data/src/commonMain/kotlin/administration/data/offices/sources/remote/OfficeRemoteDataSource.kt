package administration.data.offices.sources.remote

import administration.data.PackageLevelAccess
import administration.data.entity.AdminOfficeListEntity

@OptIn(PackageLevelAccess::class)
interface OfficeRemoteDataSource {
    suspend fun getOffices( token: String): Result<AdminOfficeListEntity>

}