package com.just.cse.digital_diary.two_zero_two_three.data_layer.admin_sub_offices.data_sources.remote

import com.just.cse.digital_diary.two_zero_two_three.data_layer.admin_sub_offices.dto.SubOfficeListResponseDTO
import com.just.cse.digitaldiary.twozerotwothree.core.network.ktor_clinet.get.Header
import com.just.cse.digitaldiary.twozerotwothree.core.network.ktor_clinet.get.getRequest
import com.just.cse.digitaldiary.twozerotwothree.core.network.ktor_clinet.post.NetworkResponse

class AdminSubOfficeListRemoteDataSource(
    officeId: String,
    private val token: String?
) {
    private val baseUrl = "https://diary.rnzgoldenventure.com/api/sub-offices/$officeId"

    suspend fun getSubOffices(): NetworkResponse<SubOfficeListResponseDTO> {
        if (token == null)
            return NetworkResponse(
                result = null,
                reason = null,
                isSuccess = false
            )
        val header = Header(key = "Authorization", value = token)
        return getRequest<SubOfficeListResponseDTO>(url = baseUrl, header = header)
    }
}