package com.just.cse.digital_diary.features.faculty.destination.event

import com.just.cse.digital_diary.features.faculty.components.event.FacultyEvent
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.teachers.event.TeacherListEvent

    /**
     * Used as convert or separate between the [FacultyEvent] so that client module does not need to depends
     * on the lower module
     */
   sealed interface FacultyFeatureEvent {
        data class CallRequest(val number: String) : FacultyFeatureEvent
        data class MessageRequest(val number: String) : FacultyFeatureEvent
        data class EmailRequest(val email: String) : FacultyFeatureEvent
        data object ExitRequest:FacultyFeatureEvent
    }
