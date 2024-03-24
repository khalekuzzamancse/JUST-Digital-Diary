package administration.data.offices.data_sources.remote

import administration.data.offices.data_sources.remote.entity.AdminOfficeListEntity
import com.just.cse.digitaldiary.twozerotwothree.core.network.ktor_clinet.get.Header
import com.just.cse.digitaldiary.twozerotwothree.core.network.ktor_clinet.get.getRequest2

class AdminOfficeListRemoteDataSource(
    private val token: String?
) {
    private val url = "https://diary.rnzgoldenventure.com/api/get/offices"

    suspend fun getOffices(): Result<AdminOfficeListEntity> {
        if (token == null)
            return Result.failure(Throwable("Token is Null"))
        val header = Header(key = "Authorization", value = token)
        return getRequest2<AdminOfficeListEntity>(url = url, header = header)
    }
}