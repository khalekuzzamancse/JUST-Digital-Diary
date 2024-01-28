package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.admin_offices.faculty_list.state

data class FacultyListState(
    val faculties: List<Faculty> = emptyList(),
    val selected:Int=-1
)
