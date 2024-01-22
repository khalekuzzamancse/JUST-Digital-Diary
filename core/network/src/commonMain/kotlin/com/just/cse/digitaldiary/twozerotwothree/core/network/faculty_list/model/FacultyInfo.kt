package com.just.cse.digitaldiary.twozerotwothree.core.network.faculty_list.model

import kotlinx.serialization.Serializable

@Serializable
data class FacultyInfo(
    val id: Int,
    val faculty_id: String,
    val name: String,
    val departmentsCount: Int
)