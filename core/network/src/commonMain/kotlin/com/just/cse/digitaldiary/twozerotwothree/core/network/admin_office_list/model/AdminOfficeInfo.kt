package com.just.cse.digitaldiary.twozerotwothree.core.network.admin_office_list.model

import kotlinx.serialization.Serializable

@Serializable
data class AdminOfficeInfo(
    val id: Int,
    val ad_office_id: String,
    val name: String,
    val sub_offices_count: Int
)

