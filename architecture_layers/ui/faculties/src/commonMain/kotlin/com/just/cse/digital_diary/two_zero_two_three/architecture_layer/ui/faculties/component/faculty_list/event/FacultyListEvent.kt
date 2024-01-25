package com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.faculties.component.faculty_list.event

import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.faculties.destination.event.FacultyDestinationEvent

interface FacultyListEvent:FacultyDestinationEvent {
    data class FacultySelected(val index:Int):FacultyListEvent
}