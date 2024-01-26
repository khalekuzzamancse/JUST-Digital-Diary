package com.just.cse.digital_diary.two_zero_two_three.department.component.event

import com.just.cse.digital_diary.two_zero_two_three.department.destinations.event.DepartmentInfoEvent

interface DepartmentInfoNavigationEvent: DepartmentInfoEvent {
    data class DestinationSelected(val index: Int) : DepartmentInfoNavigationEvent
}