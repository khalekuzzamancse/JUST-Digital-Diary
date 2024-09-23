package miscellaneous.ui.route


import androidx.compose.runtime.Composable
import miscellaneous.ui.home.home.HomeDestination
import miscellaneous.ui.home.home.HomeDestinationEvent

@Composable
fun Home(
    universityLogo: @Composable () -> Unit = {},
    university: @Composable () -> Unit = {},
    onEvent:(OtherFeatureFunctionalityEvent)->Unit
) {
    HomeDestination { event ->

        onEvent(event._toOtherFeatureFunctionalityEvent())
    }

}
fun HomeDestinationEvent._toOtherFeatureFunctionalityEvent(): OtherFeatureFunctionalityEvent {
    return when (this) {
        is HomeDestinationEvent.CalenderViewRequest -> OtherFeatureFunctionalityEvent.CalenderRequest(this.url)
        HomeDestinationEvent.NavigateToFacultyList -> OtherFeatureFunctionalityEvent.NavigateToFacultyList
        HomeDestinationEvent.NavigateTAdminOfficeList -> OtherFeatureFunctionalityEvent.NavigateTAdminOfficeList
        HomeDestinationEvent.NavigateToCalendarUpdate -> OtherFeatureFunctionalityEvent.NavigateToCalendarUpdate
        HomeDestinationEvent.NavigateToExamRoutineUpdate -> OtherFeatureFunctionalityEvent.NavigateToExamRoutineUpdate
        HomeDestinationEvent.NavigateToClassRoutineUpdate -> OtherFeatureFunctionalityEvent.NavigateToClassRoutineUpdate
        HomeDestinationEvent.NavigateToTeacherInfoUpdate -> OtherFeatureFunctionalityEvent.NavigateToTeacherInfoUpdate
        HomeDestinationEvent.NavigationRequest -> error("NavigationRequest is not mappable to OtherFeatureFunctionalityEvent")
    }
}