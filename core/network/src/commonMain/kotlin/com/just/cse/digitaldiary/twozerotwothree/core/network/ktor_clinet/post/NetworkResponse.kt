package com.just.cse.digitaldiary.twozerotwothree.core.network.ktor_clinet.post



data class NetworkResponse<T>(
    val result: T?,
    val reason: String?,
    val isSuccess: Boolean,
)