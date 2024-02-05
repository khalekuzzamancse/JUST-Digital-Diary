package com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.departments.event

interface DepartmentDestinationEvent {
    data object ExitRequest:DepartmentDestinationEvent
    interface DepartmentListEvent : DepartmentDestinationEvent {
        data class DepartmentSelected(val index: Int) : DepartmentListEvent
        data object DismissRequest: DepartmentListEvent
    }
}
