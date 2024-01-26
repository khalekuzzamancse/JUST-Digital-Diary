package com.just.cse.digital_diary.two_zero_two_three.data_layer.admin_officers.data_sources.remote

import com.just.cse.digital_diary.two_zero_two_three.data_layer.admin_officers.dto.AdminOfficerListDTO
import com.just.cse.digitaldiary.twozerotwothree.core.network.ktor_clinet.get.Header
import com.just.cse.digitaldiary.twozerotwothree.core.network.ktor_clinet.get.getRequest

class AdminOfficerListRemoteDataSource(subOfficeId: String) {
    private val baseUrl="https://diary.rnzgoldenventure.com/api/get-admin-members/$subOfficeId"
    private val token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOiIxOTExMDEuY3NlOGFlNDdkYTdkY2VkIiwicm9sZV9pZCI6MTMsImlhdCI6MTcwNjI4Mjg1MCwiZXhwIjoxNzA2NDU1NjUwfQ.S1lFy5HhNpFKsLAwYuiZapIsKs8Ol-bHs3QkhyOgKss"
    private val header = Header(key="Authorization", value = token)
    suspend fun getOfficers()= getRequest<AdminOfficerListDTO>(url=baseUrl, header=header)
}