package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.admin_officers.component.employee_list.event

import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.admin_officers.destination.event.AdminOfficersDestinationEvent

interface AdminEmployeeListEvent: AdminOfficersDestinationEvent {
    data class CallRequest(val number:String): AdminEmployeeListEvent
    data class MessageRequest(val number:String): AdminEmployeeListEvent
    data class EmailRequest(val email:String): AdminEmployeeListEvent

}