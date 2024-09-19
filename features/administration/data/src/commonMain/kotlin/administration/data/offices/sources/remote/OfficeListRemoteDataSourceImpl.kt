package administration.data.offices.sources.remote

import administration.data.PackageLevelAccess
import administration.data.offices.sources.remote.entity.AdminOfficeListEntity
import _old.network.get.Header
import _old.network.get.getRequest

internal class OfficeListRemoteDataSourceImpl :OfficeRemoteDataSource{
    private val url = "https://diary.rnzgoldenventure.com/api/get/offices"
    @OptIn(PackageLevelAccess::class) // Permits access to "AdminOfficerListEntity" within this parent package
    override suspend fun getOffices(token:String): Result<AdminOfficeListEntity> {
        val header = Header(key = "Authorization", value = token)
        return getRequest<AdminOfficeListEntity>(url = url, header = header)
    }
}