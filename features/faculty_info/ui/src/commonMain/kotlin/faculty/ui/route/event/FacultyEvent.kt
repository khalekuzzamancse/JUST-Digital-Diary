package faculty.ui.route.event

/**
 * Used as convert or separate between the [TeacherListEvent] so that client module does not need to depends
 * on the lower module
 */
interface FacultyEvent {
    data class CallRequest(val number: String) : FacultyEvent
    data class MessageRequest(val number: String) : FacultyEvent
    data class EmailRequest(val email: String) : FacultyEvent
}