package miscellaneous.ui.route

/**
 * It is a separate to prevent public low level event,that should not be care about the client module
 */
sealed interface OtherFeatureFunctionalityEvent {
    data class CalenderRequest(val url:String): OtherFeatureFunctionalityEvent
    object NavigateToFacultyList: OtherFeatureFunctionalityEvent
    object NavigateTAdminOfficeList: OtherFeatureFunctionalityEvent
    object NavigateToCalendarUpdate : OtherFeatureFunctionalityEvent
    object NavigateToExamRoutineUpdate : OtherFeatureFunctionalityEvent
    object NavigateToClassRoutineUpdate : OtherFeatureFunctionalityEvent
    object NavigateToTeacherInfoUpdate : OtherFeatureFunctionalityEvent
}
