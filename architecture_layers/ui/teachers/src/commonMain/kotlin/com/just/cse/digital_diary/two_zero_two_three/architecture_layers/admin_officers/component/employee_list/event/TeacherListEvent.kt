package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.admin_officers.component.employee_list.event

import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.admin_officers.destination.event.TeachersDestinationEvent

interface TeacherListEvent: TeachersDestinationEvent {
    data class CallRequest(val number:String): TeacherListEvent
    data class MessageRequest(val number:String): TeacherListEvent
    data class EmailRequest(val email:String): TeacherListEvent

}