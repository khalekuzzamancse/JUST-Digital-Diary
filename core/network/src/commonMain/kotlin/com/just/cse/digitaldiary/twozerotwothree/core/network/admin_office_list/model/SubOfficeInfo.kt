package com.just.cse.digitaldiary.twozerotwothree.core.network.admin_office_list.model

import kotlinx.serialization.Serializable

@Serializable
data class SubOfficeInfo(
    val id: Int,
    val sub_office_id: String,
    val name: String,
    val members_count: Int
)
