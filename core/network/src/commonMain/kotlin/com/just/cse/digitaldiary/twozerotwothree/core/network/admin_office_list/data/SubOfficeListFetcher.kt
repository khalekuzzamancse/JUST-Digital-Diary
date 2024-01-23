package com.just.cse.digitaldiary.twozerotwothree.core.network.admin_office_list.data

import com.just.cse.digitaldiary.twozerotwothree.core.network.AuthManager
import com.just.cse.digitaldiary.twozerotwothree.core.network.admin_office_list.model.SubOfficeInfo
import com.just.cse.digitaldiary.twozerotwothree.core.network.admin_office_list.model.SubOfficeListResponse
import com.just.cse.digitaldiary.twozerotwothree.core.network.deparment_list.model.DepartmentInfo
import com.just.cse.digitaldiary.twozerotwothree.core.network.deparment_list.model.DepartmentListResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json

class SubOfficeListFetcher {
    private val baseUrl="https://diary.rnzgoldenventure.com/api/sub-offices/"
    private val authToken=AuthManager.authToken
    suspend fun fetch(officeId:String):List<SubOfficeInfo> {
        val httpClient = HttpClient {
            install(ContentNegotiation) {
                json()
            }
        }
        return if (authToken!=null){
            try {
                val response = httpClient.get(baseUrl+officeId){
                    header("Authorization", authToken)
                }.body<SubOfficeListResponse>()
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