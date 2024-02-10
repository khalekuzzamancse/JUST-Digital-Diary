package com.just.cse.digital_diary.two_zero_two_three.data_layer.login.data_sources.remote

data  class Response<T>(
    val result:T?,
    val reason:String?
)