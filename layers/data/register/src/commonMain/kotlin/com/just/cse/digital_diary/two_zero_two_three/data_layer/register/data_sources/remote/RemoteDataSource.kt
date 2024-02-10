package com.just.cse.digital_diary.two_zero_two_three.data_layer.register.data_sources.remote

import com.just.cse.digital_diary.two_zero_two_three.data_layer.register.dto.RegisterResponseDTO
import com.just.cse.digitaldiary.twozerotwothree.core.network.ktor_clinet.post.NetworkResponse
import com.just.cse.digitaldiary.twozerotwothree.core.network.ktor_clinet.post.post

internal class RemoteDataSource {
    private val url = "https://diary.rnzgoldenventure.com/api/register"
    suspend fun register(body: RegisterBody): NetworkResponse<RegisterResponseDTO> {
        return post<RegisterResponseDTO>(
            url = url,
            body = body
        )
    }
}