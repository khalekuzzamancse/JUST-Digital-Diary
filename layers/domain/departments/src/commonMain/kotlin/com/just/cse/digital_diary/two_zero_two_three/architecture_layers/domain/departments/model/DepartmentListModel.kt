package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.departments.model

import java.util.UUID

data class DepartmentListModel(
    val id: String,
    val name: String,
    val shortName: String,
    val employeeCount: Int
)