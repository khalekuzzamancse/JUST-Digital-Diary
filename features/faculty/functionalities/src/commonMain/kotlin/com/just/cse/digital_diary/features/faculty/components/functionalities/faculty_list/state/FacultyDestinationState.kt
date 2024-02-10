package com.just.cse.digital_diary.features.faculty.components.functionalities.faculty_list.state

data class FacultiesScreenState(
    val isLoading: Boolean = false,
    val message: String? = null,
    val openDepartmentListDestination: Boolean=false
)
