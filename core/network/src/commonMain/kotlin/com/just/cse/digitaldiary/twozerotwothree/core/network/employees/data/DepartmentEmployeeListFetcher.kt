package com.just.cse.digitaldiary.twozerotwothree.core.network.employees.data

import com.just.cse.digitaldiary.twozerotwothree.core.network.AuthManager
import com.just.cse.digitaldiary.twozerotwothree.core.network.employees.model.Employee
import com.just.cse.digitaldiary.twozerotwothree.core.network.employees.model.EmployeeListResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json

class DepartmentEmployeeListFetcher {
    private val baseUrl="https://diary.rnzgoldenventure.com/api/department/"
    private val authToken=AuthManager.authToken
    suspend fun fetch(deptId:String):List<Employee> {
        val httpClient = HttpClient {
            install(ContentNegotiation) {
                json()
            }
        }
        return if (authToken!=null){
            try {
                val response = httpClient.get(baseUrl+deptId){
                    header("Authorization", authToken)
                }.body<EmployeeListResponse>()
                println("DepartmentListFetcherClass: success:$response")
                response.data
            } catch (_: Exception) {
                println("DepartmentListFetcherClass: Failed")
                emptyList()
            }
        } else {
            println("DepartmentListFetcherClass:Authentication Token is NUll")
            emptyList()
        }


    }
}