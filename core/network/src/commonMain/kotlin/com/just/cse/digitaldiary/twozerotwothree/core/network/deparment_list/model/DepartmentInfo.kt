package com.just.cse.digitaldiary.twozerotwothree.core.network.deparment_list.model

import kotlinx.serialization.Serializable

@Serializable
data class DepartmentInfo(
    val id: Int,
    val dept_id: String,
    val name: String,
    val shortname: String,
    val membersCount: Int
)