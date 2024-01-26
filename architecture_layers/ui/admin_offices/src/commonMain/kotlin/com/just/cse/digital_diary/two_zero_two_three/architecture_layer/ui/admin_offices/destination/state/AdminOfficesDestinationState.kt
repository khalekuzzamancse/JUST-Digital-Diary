package com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.admin_offices.destination.state

import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.admin_offices.component.faculty_list.state.FacultyListState

data class AdminOfficesDestinationState(
    val isLoading: Boolean=false,
    val message: String?=null,
    val facultyListState: FacultyListState=FacultyListState(),

)
