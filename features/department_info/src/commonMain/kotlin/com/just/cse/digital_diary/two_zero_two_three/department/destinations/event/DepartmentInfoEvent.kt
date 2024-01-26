package com.just.cse.digital_diary.two_zero_two_three.department.destinations.event

interface DepartmentInfoEvent {

    data class CallRequest(val number: String): DepartmentInfoEvent
    data class MessageRequest(val number: String): DepartmentInfoEvent
    data class EmailRequest(val email: String): DepartmentInfoEvent
    data object ExitRequest: DepartmentInfoEvent
}