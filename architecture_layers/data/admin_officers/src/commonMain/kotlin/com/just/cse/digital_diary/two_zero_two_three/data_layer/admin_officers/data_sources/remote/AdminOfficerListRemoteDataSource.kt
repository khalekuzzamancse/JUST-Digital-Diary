package com.just.cse.digital_diary.two_zero_two_three.data_layer.admin_officers.data_sources.remote

import com.just.cse.digital_diary.two_zero_two_three.data_layer.admin_officers.dto.AdminOfficerListDTO
import com.just.cse.digitaldiary.twozerotwothree.core.network.ktor_clinet.get.Header
import com.just.cse.digitaldiary.twozerotwothree.core.network.ktor_clinet.get.getRequest
import com.just.cse.digitaldiary.twozerotwothree.core.network.ktor_clinet.post.NetworkResponse

class AdminOfficerListRemoteDataSource(
    private val token: String?,
    subOfficeId: String
) {
    private val baseUrl = "https://diary.rnzgoldenventure.com/api/get-admin-members/$subOfficeId"

    suspend fun getOfficers(): NetworkResponse<AdminOfficerListDTO> {
        if (token == null) return NetworkResponse(
            result = null,
            reason = "Token is null",
            isSuccess = false
        )
        val header = Header(key = "Authorization", value = token)
        return getRequest<AdminOfficerListDTO>(url = baseUrl, header = header)
    }
}