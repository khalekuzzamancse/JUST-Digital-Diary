package miscellaneous

import miscellaneous.ui.route.OtherFeatureFunctionalityEvent

/**
 * It is a separate to prevent public low level event,that should not be care about the client module
 */
interface OtherFeatureEvent {
    data class CalenderRequest(val url:String): OtherFeatureEvent
    object NavigateToFacultyList: OtherFeatureEvent
    object NavigateTAdminOfficeList: OtherFeatureEvent
    object NavigateToCalendarUpdate : OtherFeatureEvent
    object NavigateToExamRoutineUpdate : OtherFeatureEvent
    object NavigateToClassRoutineUpdate : OtherFeatureEvent
    object NavigateToTeacherInfoUpdate : OtherFeatureEvent
}