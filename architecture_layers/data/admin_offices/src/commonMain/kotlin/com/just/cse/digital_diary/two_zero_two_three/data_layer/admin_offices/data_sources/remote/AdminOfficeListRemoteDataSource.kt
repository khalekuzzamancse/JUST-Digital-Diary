package com.just.cse.digital_diary.two_zero_two_three.data_layer.admin_offices.data_sources.remote

import com.just.cse.digital_diary.two_zero_two_three.data_layer.admin_offices.dto.AdminOfficeListRemoteResponseDTO
import com.just.cse.digitaldiary.twozerotwothree.core.network.ktor_clinet.get.Header
import com.just.cse.digitaldiary.twozerotwothree.core.network.ktor_clinet.get.getRequest
import com.just.cse.digitaldiary.twozerotwothree.core.network.ktor_clinet.post.NetworkResponse

class AdminOfficeListRemoteDataSource(
    private val token: String?
) {
    private val url = "https://diary.rnzgoldenventure.com/api/admin-offices"

    suspend fun getOffices(): NetworkResponse<AdminOfficeListRemoteResponseDTO> {
        if (token == null)
            return NetworkResponse(
                result = null,
                reason = "Token is null",
                isSuccess = false
            )
        val header = Header(key = "Authorization", value = token)
        return getRequest<AdminOfficeListRemoteResponseDTO>(url = url, header = header)
    }
}