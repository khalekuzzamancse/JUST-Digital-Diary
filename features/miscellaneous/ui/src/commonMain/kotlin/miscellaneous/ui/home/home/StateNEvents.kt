@file:Suppress("UnUsed")
package miscellaneous.ui.home.home

sealed interface HomeDestinationEvent {
    data class CalenderViewRequest(val url:String) : HomeDestinationEvent
    data object NavigationRequest : HomeDestinationEvent
    object NavigateToFacultyList:HomeDestinationEvent
    object NavigateTAdminOfficeList:HomeDestinationEvent
    object NavigateToCalendarUpdate : HomeDestinationEvent
    object NavigateToExamRoutineUpdate : HomeDestinationEvent
    object NavigateToClassRoutineUpdate : HomeDestinationEvent
    object NavigateToTeacherInfoUpdate : HomeDestinationEvent
}