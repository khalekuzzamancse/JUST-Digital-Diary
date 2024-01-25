package com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.departments.destination.state

import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.departments.component.department_list.state.DepartmentListState

data class DepartmentDestinationState(
    val isLoading: Boolean = false,
    val snackBarMessage: String?=null,
    val departmentListState:DepartmentListState=DepartmentListState()
)
