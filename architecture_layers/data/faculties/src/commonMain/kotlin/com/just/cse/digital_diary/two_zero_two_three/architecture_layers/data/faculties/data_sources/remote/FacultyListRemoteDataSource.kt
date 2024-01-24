package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.data.faculties.data_sources.remote

import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.data.faculties.dto.FacultyListResponseDTO
import com.just.cse.digitaldiary.twozerotwothree.core.network.ktor_clinet.get.Header
import com.just.cse.digitaldiary.twozerotwothree.core.network.ktor_clinet.get.getRequest

class FacultyListRemoteDataSource {
    private val token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOiIxOTExMDEuY3NlOGFlNDdkYTdkY2VkIiwicm9sZV9pZCI6MTMsImlhdCI6MTcwNjEwNjI4NiwiZXhwIjoxNzA2Mjc5MDg2fQ.WdLDU_smb_tUoV8daagmdTeOK271QKNlSuqr7tQA14g"
    private val url="https://diary.rnzgoldenventure.com/api/faculties"
    private val header =Header(key="Authorization", value = token)
    suspend fun getFaculties()= getRequest<FacultyListResponseDTO>(url=url, header=header)
}