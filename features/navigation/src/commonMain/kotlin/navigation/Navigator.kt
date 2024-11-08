package navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import miscellaneous.MiscFeatureEvent
import navigation.component.NavDestination
import profile.presentationlogic.ProfileEvent

class Navigator(
    private val navController: NavHostController,
    private val onEvent: (AppEvent) -> Unit,
) {

    internal fun navigator(destination: Destination) {
        when (destination) {
            is NavDestination.ExploreJust -> {
                onEvent(AppEvent.WebVisitRequest(""))//will be modified to UI
            }
            else -> {
                navigateAsTopMostDestination(destination.route)
            }
        }
    }

    fun onMiscFeatureEvent(event: MiscFeatureEvent) {
        try {
            when (event) {
                is MiscFeatureEvent.CalenderRequest -> {
                    onEvent(AppEvent.WebVisitRequest(event.url))
                }

                is MiscFeatureEvent.NavigateToFacultyList -> {
                    navigateAsTopMostDestination(NavDestination.FacultyList.route)
                }

                is MiscFeatureEvent.NavigateTAdminOfficeList -> {
                    navigateAsTopMostDestination(NavDestination.AdminOffice.route)
                }


                else -> {

                }
            }
        } catch (_: Exception) {
        }

    }


    fun pop() {
        navController.popBackStack()
    }

    private  fun navigateAsTopMostDestination(destination: String) {
        return try {

            navController.navigate(destination) {
                navController.graph.findStartDestination().route?.let {
                    popUpTo(it) {
                        saveState = true
                    }
                }
                launchSingleTop = true
                restoreState = true

            }


        } catch (_: Exception) {

        }
    }
    fun onAdminEvent(event: ProfileEvent) {

    }


}





