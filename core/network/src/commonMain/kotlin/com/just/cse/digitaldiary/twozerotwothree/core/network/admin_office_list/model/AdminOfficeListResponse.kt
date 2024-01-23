package com.just.cse.digitaldiary.twozerotwothree.core.network.admin_office_list.model

import kotlinx.serialization.Serializable

@Serializable
data class AdminOfficeListResponse(
    val message: String,
    val data: List<AdminOfficeInfo>
)