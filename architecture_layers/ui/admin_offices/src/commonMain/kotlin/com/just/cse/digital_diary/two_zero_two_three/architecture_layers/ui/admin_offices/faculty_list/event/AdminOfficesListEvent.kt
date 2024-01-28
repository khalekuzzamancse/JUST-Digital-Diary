package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.admin_offices.faculty_list.event

import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.admin_offices.destination.event.AdminOfficesDestinationEvent

interface AdminOfficesListEvent: AdminOfficesDestinationEvent {
    data class AdminOfficesSelected(val index:Int): AdminOfficesListEvent
}