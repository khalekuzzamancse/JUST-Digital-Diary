package com.just.cse.digital_diary.features.faculty.faculty.state

data class FacultiesScreenState(
    val isLoading: Boolean = false,
    val message: String? = null,
    val openDepartmentListDestination: Boolean=false
)
