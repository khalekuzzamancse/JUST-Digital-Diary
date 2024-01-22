package com.just.cse.digitaldiary.twozerotwothree.core.network.faculty_list.model

import kotlinx.serialization.Serializable

@Serializable
class FacultyListResponse(
    val message: String,
    val data: List<FacultyInfo>
)