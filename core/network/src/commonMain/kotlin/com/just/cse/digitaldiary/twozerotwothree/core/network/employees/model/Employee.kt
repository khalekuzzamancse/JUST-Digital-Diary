package com.just.cse.digitaldiary.twozerotwothree.core.network.employees.model

import kotlinx.serialization.Serializable

@Serializable
data class Employee (
    val uid: String,
    val name: String,
    val email: String,
    val additional_email: String?,
    val profile_image: String?,
    val achievement: String,
    val phone: String?,
    val designations: String,
    val department_name: String,
    val department_shortname: String,
    val room_no: String

)
