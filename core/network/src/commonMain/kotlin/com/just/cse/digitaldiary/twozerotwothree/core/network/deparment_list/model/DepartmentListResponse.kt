package com.just.cse.digitaldiary.twozerotwothree.core.network.deparment_list.model

import kotlinx.serialization.Serializable

@Serializable
data class DepartmentListResponse(
    val message: String,
    val data: List<DepartmentInfo>
)