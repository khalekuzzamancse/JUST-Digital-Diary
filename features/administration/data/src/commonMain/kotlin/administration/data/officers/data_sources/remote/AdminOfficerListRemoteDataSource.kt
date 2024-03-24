package administration.data.officers.data_sources.remote

import administration.data.officers.data_sources.remote.entity.AdminOfficerListEntity
import com.just.cse.digitaldiary.twozerotwothree.core.network.ktor_clinet.get.Header
import com.just.cse.digitaldiary.twozerotwothree.core.network.ktor_clinet.get.getRequest2

class AdminOfficerListRemoteDataSource(
    private val token: String?,
    subOfficeId: String
) {
    private val baseUrl = "https://diary.rnzgoldenventure.com/api/get/office-members/$subOfficeId"

    suspend fun getOfficers(): Result<AdminOfficerListEntity> {
        if (token == null)
            return Result.failure(Throwable("Token is Null"))
        val header = Header(key = "Authorization", value = token)
        return getRequest2<AdminOfficerListEntity>(url = baseUrl, header = header)
    }
}