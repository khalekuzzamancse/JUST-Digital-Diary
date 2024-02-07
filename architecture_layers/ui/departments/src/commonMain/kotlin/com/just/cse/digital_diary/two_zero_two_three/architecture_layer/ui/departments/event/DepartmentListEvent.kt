package com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.departments.event

interface DepartmentListEvent {
    data class DepartmentSelected(val index: Int) : DepartmentListEvent
}
