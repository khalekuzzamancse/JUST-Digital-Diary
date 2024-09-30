@file:Suppress("UnUsed")
package miscellaneous.ui.home

sealed interface HomeRouteEvent {
    data class CalenderViewRequest(val url:String) : HomeRouteEvent
    data object NavigationRequest : HomeRouteEvent
    data object NavigateToFacultyList: HomeRouteEvent
    data object NavigateTAdminOfficeList: HomeRouteEvent
    data object NavigateToCalendarUpdate : HomeRouteEvent
    data object NavigateToExamRoutineUpdate : HomeRouteEvent
    data object NavigateToClassRoutineUpdate : HomeRouteEvent
    data object NavigateToTeacherInfoUpdate : HomeRouteEvent
}