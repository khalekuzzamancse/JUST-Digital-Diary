package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.data.departments.data_sources.remote

import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.data.departments.dto.DepartmentListResponseDTO
import com.just.cse.digitaldiary.twozerotwothree.core.network.AuthManager
import com.just.cse.digitaldiary.twozerotwothree.core.network.deparment_list.model.DepartmentListResponse
import com.just.cse.digitaldiary.twozerotwothree.core.network.ktor_clinet.get.Header
import com.just.cse.digitaldiary.twozerotwothree.core.network.ktor_clinet.get.getRequest

class DepartmentListRemoteDataSource(facultyId:String) {
    private val baseUrl="https://diary.rnzgoldenventure.com/api/dept/$facultyId"
    private val token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOiIxOTExMDEuY3NlOGFlNDdkYTdkY2VkIiwicm9sZV9pZCI6MTMsImlhdCI6MTcwNjQxOTc3OSwiZXhwIjoxNzA2NTkyNTc5fQ.AxaN98L4p_lcxqJR5wql-qIVJTHGdN1Ju4Q584PX1iw"
    private val header = Header(key="Authorization", value = token)
    suspend fun getDepartments()= getRequest<DepartmentListResponseDTO>(url=baseUrl, header=header)
}