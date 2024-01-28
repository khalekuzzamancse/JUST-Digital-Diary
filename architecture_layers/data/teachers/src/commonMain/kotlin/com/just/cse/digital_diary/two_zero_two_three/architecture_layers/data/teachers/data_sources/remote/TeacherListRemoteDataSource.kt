package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.data.teachers.data_sources.remote

import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.data.teachers.dto.TeacherListDTO
import com.just.cse.digitaldiary.twozerotwothree.core.network.ktor_clinet.get.Header
import com.just.cse.digitaldiary.twozerotwothree.core.network.ktor_clinet.get.getRequest

class TeacherListRemoteDataSource(deptId: String) {
    private val baseUrl="https://diary.rnzgoldenventure.com/api/department/$deptId"
    private val token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOiIxOTExMDEuY3NlOGFlNDdkYTdkY2VkIiwicm9sZV9pZCI6MTMsImlhdCI6MTcwNjQxOTc3OSwiZXhwIjoxNzA2NTkyNTc5fQ.AxaN98L4p_lcxqJR5wql-qIVJTHGdN1Ju4Q584PX1iw"
    private val header = Header(key="Authorization", value = token)
    suspend fun getTeachers()= getRequest<TeacherListDTO>(url=baseUrl, header=header)
}