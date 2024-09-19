package administration.data.officers.data_sources.remote

import administration.data.PackageLevelAccess
import administration.data.officers.data_sources.remote.entity.AdminOfficerListEntity
import _old.network.get.Header
import _old.network.get.getRequest

internal class AdminOfficerListRemoteDataSource(
    private val token: String?,
    subOfficeId: String
) {
    private val baseUrl = "https://diary.rnzgoldenventure.com/api/get/office-members/$subOfficeId"

    @OptIn(PackageLevelAccess::class) // Permits access to "AdminOfficerListEntity" within this package
    suspend fun getOfficers(): Result<AdminOfficerListEntity> {
        if (token == null)
            return Result.failure(Throwable("Token is Null"))
        val header = Header(key = "Authorization", value = token)
        return getRequest<AdminOfficerListEntity>(url = baseUrl, header = header)
    }
}