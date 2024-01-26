package com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.faculties.destination.state

import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.faculties.component.faculty_list.state.FacultyListState

data class FacultyDestinationState(
    val isLoading: Boolean=false,
    val message: String?=null,
    val facultyListState: FacultyListState=FacultyListState(),

)
