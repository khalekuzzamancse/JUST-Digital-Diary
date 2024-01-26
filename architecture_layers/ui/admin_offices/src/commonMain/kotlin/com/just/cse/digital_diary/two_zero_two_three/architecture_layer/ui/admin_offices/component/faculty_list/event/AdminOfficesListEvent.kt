package com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.admin_offices.component.faculty_list.event

import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.admin_offices.destination.event.AdminOfficesDestinationEvent

interface AdminOfficesListEvent:AdminOfficesDestinationEvent {
    data class AdminOfficesSelected(val index:Int):AdminOfficesListEvent
}