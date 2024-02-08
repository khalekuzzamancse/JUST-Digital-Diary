package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.data.departments.dto

import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.departments.model.DepartmentListModel
import kotlinx.serialization.Serializable

//response JSON Format
@Serializable
data class DepartmentInfoDTO(
    val id: Int,
    val dept_id: String,
    val name: String,
    val shortname: String,
    val membersCount: Int
){
    fun toModel()= DepartmentListModel(
        name = name,
        id = dept_id,
        shortName = shortname,
        employeeCount = membersCount,
    )
}