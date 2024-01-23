package com.just.cse.digitaldiary.twozerotwothree.core.network.admin_office_list.model

import kotlinx.serialization.Serializable

@Serializable
data class AdminEmployee (
    val uid: String,
    val name: String,
    val email: String,
    val additional_email: String?,
    val profile_image: String?,
    val achievement: String,
    val phone: String?,
    val designations: String,
    val room_no: String

)
