package administration.data.sub_office.data_sources.remote

import administration.data.PackageLevelAccess
import administration.data.sub_office.data_sources.remote.entity.SubOfficeListEntity
import core.network.get.Header
import core.network.get.getRequest

internal class SubOfficeRemoteDataSourceImpl(
    officeId: String
) :SubOfficeRemoteDataSource{
    private val baseUrl = "https://diary.rnzgoldenventure.com/api/get/sub-offices/$officeId"
    @PackageLevelAccess
    override suspend fun getSubOffices(token: String, officeId: String,): Result<SubOfficeListEntity> {
        val header = Header(key = "Authorization", value = token)
        return getRequest<SubOfficeListEntity>(url = baseUrl, header = header)
    }
}