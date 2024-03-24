package administration.data.sub_office.data_sources.remote

import administration.data.sub_office.data_sources.remote.entity.SubOfficeListEntity
import com.just.cse.digitaldiary.twozerotwothree.core.network.ktor_clinet.get.Header
import com.just.cse.digitaldiary.twozerotwothree.core.network.ktor_clinet.get.getRequest2

class AdminSubOfficeListRemoteDataSource(
    officeId: String,
    private val token: String?
) {
    private val baseUrl = "https://diary.rnzgoldenventure.com/api/get/sub-offices/$officeId"

    suspend fun getSubOffices(): Result<SubOfficeListEntity> {
        if (token == null)
            return Result.failure(Throwable("Token is Null"))
        val header = Header(key = "Authorization", value = token)
        return getRequest2<SubOfficeListEntity>(url = baseUrl, header = header)
    }
}