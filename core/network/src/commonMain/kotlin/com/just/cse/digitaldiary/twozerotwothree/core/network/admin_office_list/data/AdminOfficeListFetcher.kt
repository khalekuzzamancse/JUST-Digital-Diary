package com.just.cse.digitaldiary.twozerotwothree.core.network.admin_office_list.data

import com.just.cse.digitaldiary.twozerotwothree.core.network.AuthManager
import com.just.cse.digitaldiary.twozerotwothree.core.network.admin_office_list.model.AdminOfficeInfo
import com.just.cse.digitaldiary.twozerotwothree.core.network.admin_office_list.model.AdminOfficeListResponse
import com.just.cse.digitaldiary.twozerotwothree.core.network.faculty_list.model.FacultyInfo
import com.just.cse.digitaldiary.twozerotwothree.core.network.faculty_list.model.FacultyListResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json

class AdminOfficeListFetcher {
    private val url="https://diary.rnzgoldenventure.com/api/admin-offices"
    private val authToken=AuthManager.authToken
    suspend fun fetch():List<AdminOfficeInfo> {
        val httpClient = HttpClient {
            install(ContentNegotiation) {
                json()
            }
        }
        return if (authToken!=null){
            try {
                val response = httpClient.get(url){
                    header("Authorization", authToken)
                }.body<AdminOfficeListResponse>()
                println("FacultyListFetcherClass:Authentication sucess:$response")
                response.data
            } catch (_: Exception) {
                emptyList()
            }
        } else {
            println("FacultyListFetcherClass:Authentication Token is NUll")
            emptyList()
        }


    }
}