package com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.admin_offices.event

interface FacultyListEvent {
    data class FacultySelected(val index: Int) : FacultyListEvent
}
