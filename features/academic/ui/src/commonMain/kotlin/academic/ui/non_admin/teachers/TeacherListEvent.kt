package academic.ui.non_admin.teachers

interface TeacherListEvent {
    data class CallRequest(val number: String) : TeacherListEvent
    data class MessageRequest(val number: String) : TeacherListEvent
    data class EmailRequest(val email: String) : TeacherListEvent


}