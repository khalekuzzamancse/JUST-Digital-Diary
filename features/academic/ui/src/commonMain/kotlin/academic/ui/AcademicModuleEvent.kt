package academic.ui

/**
 * - Defines the events that should be exposed from this module to the consumer module
 * - If some events are internal, mark them as `internal`
 * - In the future, if new events need to be added, simply add them here to maintain the `single source` of truth` for events
 */
sealed interface AcademicModuleEvent {
    interface AdminEvent : AcademicModuleEvent
    data class CallRequest(val number: String) : AcademicModuleEvent
    data class MessageRequest(val number: String) : AcademicModuleEvent
    data class EmailRequest(val email: String) : AcademicModuleEvent


    data class UpdateFacultyRequest(val id: String) : AdminEvent
    data class DeleteFacultyRequest(val id: String) : AdminEvent
    data class UpdateDeptRequest(val id: String) : AdminEvent
    data class DeleteDeptRequest(val id: String) : AdminEvent
    data class UpdateTeacherRequest(val id: String) : AdminEvent
    data class TeacherDeleteRequest(val id: String) : AdminEvent


}