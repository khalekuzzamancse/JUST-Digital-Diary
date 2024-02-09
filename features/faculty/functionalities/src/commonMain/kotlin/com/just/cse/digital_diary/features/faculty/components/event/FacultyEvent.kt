package com.just.cse.digital_diary.features.faculty.components.event

import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.teachers.event.TeacherListEvent

/**
 * Used as convert or separate between the [TeacherListEvent] so that client module does not need to depends
 * on the lower module
 */
interface FacultyEvent {
    data class CallRequest(val number: String) : FacultyEvent
    data class MessageRequest(val number: String) : FacultyEvent
    data class EmailRequest(val email: String) : FacultyEvent
}