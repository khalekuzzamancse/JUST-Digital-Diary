package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.data.faculties.data_sources.remote

import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.data.faculties.data_sources.remote.dto.FacultyListResponseDTO
import com.just.cse.digitaldiary.twozerotwothree.core.network.ktor_clinet.get.Header
import com.just.cse.digitaldiary.twozerotwothree.core.network.ktor_clinet.get.getRequest

class FacultyListRemoteDataSource {
    private val token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOiIxOTExMDEuY3NlOGFlNDdkYTdkY2VkIiwicm9sZV9pZCI6MTMsImlhdCI6MTcwNjI0NDY2OCwiZXhwIjoxNzA2NDE3NDY4fQ.L_dE8lFPZcvHlog6--dU2qpNHFqcKpZ8nyEOi1HbdAY"
    private val url="https://diary.rnzgoldenventure.com/api/faculties"
    private val header =Header(key="Authorization", value = token)
    suspend fun getFaculties()= getRequest<FacultyListResponseDTO>(url=url, header=header)
}