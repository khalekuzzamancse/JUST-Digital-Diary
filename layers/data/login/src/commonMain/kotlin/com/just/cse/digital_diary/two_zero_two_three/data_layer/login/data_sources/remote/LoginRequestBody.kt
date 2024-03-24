package com.just.cse.digital_diary.two_zero_two_three.data_layer.login.data_sources.remote

import kotlinx.serialization.Serializable

/**
this body will convert  to json as
*
  * {
  * "identifier": "...",
  *"password": "..."
  * }
 */
@Serializable
data class LoginRequestBody(
    val identifier: String,
    val password: String
)