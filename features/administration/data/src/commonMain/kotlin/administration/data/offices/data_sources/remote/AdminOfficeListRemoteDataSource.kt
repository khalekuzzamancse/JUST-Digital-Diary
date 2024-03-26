package administration.data.offices.data_sources.remote

import administration.data.PackageLevelAccess
import administration.data.offices.data_sources.remote.entity.AdminOfficeListEntity
import core.network.get.Header
import core.network.get.getRequest

internal class AdminOfficeListRemoteDataSource(
    private val token: String?
) {
    private val url = "https://diary.rnzgoldenventure.com/api/get/offices"
    @OptIn(PackageLevelAccess::class) // Permits access to "AdminOfficerListEntity" within this parent package
    suspend fun getOffices(): Result<AdminOfficeListEntity> {
        if (token == null)
            return Result.failure(Throwable("Token is Null"))
        val header = Header(key = "Authorization", value = token)
        return getRequest<AdminOfficeListEntity>(url = url, header = header)
    }
}