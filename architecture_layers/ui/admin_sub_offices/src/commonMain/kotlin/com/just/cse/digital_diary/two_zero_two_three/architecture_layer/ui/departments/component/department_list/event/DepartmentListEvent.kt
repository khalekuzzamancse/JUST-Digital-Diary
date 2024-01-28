package com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.departments.component.department_list.event

import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.departments.destination.event.DepartmentDestinationEvent

interface DepartmentListEvent : DepartmentDestinationEvent {
    data class DepartmentSelected(val index: Int) : DepartmentListEvent
    data object DismissRequest:DepartmentListEvent
}