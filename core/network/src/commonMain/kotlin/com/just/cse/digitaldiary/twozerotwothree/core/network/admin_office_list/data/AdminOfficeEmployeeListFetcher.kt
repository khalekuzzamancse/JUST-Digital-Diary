package com.just.cse.digitaldiary.twozerotwothree.core.network.admin_office_list.data

import com.just.cse.digitaldiary.twozerotwothree.core.network.AuthManager
import com.just.cse.digitaldiary.twozerotwothree.core.network.admin_office_list.model.AdminEmployee
import com.just.cse.digitaldiary.twozerotwothree.core.network.admin_office_list.model.AdminEmployeeListResponse
import com.just.cse.digitaldiary.twozerotwothree.core.network.employees.model.Employee
import com.just.cse.digitaldiary.twozerotwothree.core.network.employees.model.EmployeeListResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json

class AdminOfficeEmployeeListFetcher {
    private val baseUrl="https://diary.rnzgoldenventure.com/api/get-admin-members/"
    private val authToken=AuthManager.authToken
    suspend fun fetch(deptId:String):List<AdminEmployee> {
        val httpClient = HttpClient {
            install(ContentNegotiation) {
                json()
            }
        }
        return if (authToken!=null){
            try {
                val response = httpClient.get(baseUrl+deptId){
                    header("Authorization", authToken)
                }.body<AdminEmployeeListResponse>()
                println("AdminOfficeEmployeeListFetcher: success:$response")
                response.data
            } catch (_: Exception) {
                println("AdminOfficeEmployeeListFetcher: Failed")
                emptyList()
            }
        } else {
            println("AdminOfficeEmployeeListFetcher:Authentication Token is NUll")
            emptyList()
        }


    }
}