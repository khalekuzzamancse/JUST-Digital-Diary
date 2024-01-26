package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.admin_officers.destination.state

import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.admin_officers.component.employee_list.state.TeacherListState

data class TeacherListDestinationState(
    val isLoading: Boolean = false,
    val message: String?=null,
    val teacherListState: TeacherListState=TeacherListState(),
)
