package administration.data.sub_office.data_sources.remote

import administration.data.PackageLevelAccess
import administration.data.sub_office.data_sources.remote.entity.SubOfficeListEntity
import core.network.get.Header
import core.network.get.getRequest

internal class AdminSubOfficeListRemoteDataSource(
    officeId: String,
    private val token: String?
) {
    private val baseUrl = "https://diary.rnzgoldenventure.com/api/get/sub-offices/$officeId"
    @OptIn(PackageLevelAccess::class) // Permits access to "SubOfficeListEntity" within  this parent package
    suspend fun getSubOffices(): Result<SubOfficeListEntity> {
        if (token == null)
            return Result.failure(Throwable("Token is Null"))
        val header = Header(key = "Authorization", value = token)
        return getRequest<SubOfficeListEntity>(url = baseUrl, header = header)
    }
}