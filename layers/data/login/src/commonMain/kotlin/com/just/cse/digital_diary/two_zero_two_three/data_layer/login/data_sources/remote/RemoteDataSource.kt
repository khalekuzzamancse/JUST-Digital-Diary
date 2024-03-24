package com.just.cse.digital_diary.two_zero_two_three.data_layer.login.data_sources.remote

import com.just.cse.digital_diary.two_zero_two_three.data_layer.login.dto.RemoteResponseDTO
import com.just.cse.digitaldiary.twozerotwothree.core.network.ktor_clinet.post.post

 class RemoteDataSource {
    private val loginUrl = "https://diary.rnzgoldenventure.com/api/users/login"
    suspend fun login(username: String, password: String): Response<RemoteResponseDTO> {
        val res = post<RemoteResponseDTO>(
            url = loginUrl,
            body = LoginRequestBody(identifier =username, password = password)
        )
        return if (res.isSuccess&&res.result!=null) {
            Response(
                result = res.result,
                reason = null
            )
        } else{
            Response(
                result =null,
                reason = res.reason
            )
        }
    }
     suspend fun requestToken(username: String, password: String):String?{
         val result=RemoteDataSource().login(username = username, password = password)
         return if(result.result!=null){
             result.result.token
         } else{
             null
         }
     }
}