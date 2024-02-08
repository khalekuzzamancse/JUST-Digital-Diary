package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.teachers.event

interface TeacherListEvent {
    data class CallRequest(val number: String) : TeacherListEvent
    data class MessageRequest(val number: String) : TeacherListEvent
    data class EmailRequest(val email: String) : TeacherListEvent


}