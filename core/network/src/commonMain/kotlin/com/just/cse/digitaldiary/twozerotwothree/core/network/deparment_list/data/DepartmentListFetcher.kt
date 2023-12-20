package com.just.cse.digitaldiary.twozerotwothree.core.network.deparment_list.data

import com.just.cse.digitaldiary.twozerotwothree.core.network.AuthManager
import com.just.cse.digitaldiary.twozerotwothree.core.network.deparment_list.model.DepartmentInfo
import com.just.cse.digitaldiary.twozerotwothree.core.network.deparment_list.model.DepartmentListResponse
import com.just.cse.digitaldiary.twozerotwothree.core.network.faculty_list.model.FacultyInfo
import com.just.cse.digitaldiary.twozerotwothree.core.network.faculty_list.model.FacultyListResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json

class DepartmentListFetcher {
    private val baseUrl="https://diary.rnzgoldenventure.com/api/dept/"
    private val authToken=AuthManager.authToken
    suspend fun fetch(id:String):List<DepartmentInfo> {
        val httpClient = HttpClient {
            install(ContentNegotiation) {
                json()
            }
        }
        return if (authToken!=null){
            try {
                val response = httpClient.get(baseUrl+id){
                    header("Authorization", authToken)
                }.body<DepartmentListResponse>()
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