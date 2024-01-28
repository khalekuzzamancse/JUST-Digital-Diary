package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.teachers.destination.state

import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.teachers.employee_list.state.TeacherListState

data class TeacherListDestinationState(
    val isLoading: Boolean = false,
    val message: String?=null,
    val teacherListState: TeacherListState = TeacherListState(),
)
