package administration.ui.officers

interface AdminEmployeeListEvent {
    data class CallRequest(val number:String): AdminEmployeeListEvent
    data class MessageRequest(val number:String): AdminEmployeeListEvent
    data class EmailRequest(val email:String): AdminEmployeeListEvent

}