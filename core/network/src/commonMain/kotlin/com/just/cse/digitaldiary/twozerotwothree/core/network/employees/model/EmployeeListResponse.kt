package com.just.cse.digitaldiary.twozerotwothree.core.network.employees.model

import kotlinx.serialization.Serializable

@Serializable
data class EmployeeListResponse(
    val message: String,
    val data: List<Employee>
)
