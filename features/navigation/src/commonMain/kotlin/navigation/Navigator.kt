package navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withTimeoutOrNull
import miscellaneous.MiscFeatureEvent
import navigation.component.NavDestination

class Navigator(
    private val navController: NavHostController,
    private val onEvent: (AppEvent) -> Unit,
) {


    internal suspend fun navigator(destination: Destination): Boolean {
        return when (destination) {
            NavDestination.Home -> {
                navigateAsTopMostDestination(GraphRoutes.HOME)
            }

            NavDestination.FacultyList -> {
                navigateAsTopMostDestination(GraphRoutes.ACADEMIC_FEATURES)
            }

            NavDestination.AdminOffice -> {
                navigateAsTopMostDestination(GraphRoutes.ADMIN_OFFICE_FEATURE)
            }

            NavDestination.Search -> {
                navigateAsTopMostDestination(GraphRoutes.SEARCH)
            }

            NavDestination.NoteBook -> {
                navigateAsTopMostDestination(GraphRoutes.NOTES_FEATURE)
            }

            NavDestination.ClassSchedule -> {
                navigateAsTopMostDestination(GraphRoutes.CLASS_SCHEDULE_VIEWER)
            }

            NavDestination.ExamSchedule -> {
                navigateAsTopMostDestination(GraphRoutes.EXAM_SCHEDULE_VIEWER)
            }

            NavDestination.ExploreJust -> {
                onEvent(AppEvent.WebVisitRequest("https://just.edu.bd/"))
                true
            }

            NavDestination.AboutUs -> {
                navigateAsTopMostDestination(GraphRoutes.ABOUT_US)
            }

            NavDestination.EventGallery -> {
                navigateAsTopMostDestination(GraphRoutes.EVENT_GALLERY)
            }

            NavDestination.MessageFromVC -> {
                navigateAsTopMostDestination(GraphRoutes.VC_MESSAGES)
            }

            else -> {
                false
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
                    navController.navigate(GraphRoutes.ACADEMIC_FEATURES)
                }

                is MiscFeatureEvent.NavigateTAdminOfficeList -> {
                    navController.navigate(GraphRoutes.ADMIN_OFFICE_FEATURE)
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

    private suspend fun navigateAsTopMostDestination(destination: String): Boolean {
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
            withTimeoutOrNull(5000L) { // Timeout after 5 seconds
                navController.currentBackStackEntryFlow
                    .filter { backStackEntry ->
                        backStackEntry.destination.route == destination
                    }
                    .first() // Suspend until the destination matches
//                println("Navigation to $destination was successful")
                true
            } ?: run {
//                println("Failed to navigate to $destination within timeout")
                false
            }

        } catch (_: Exception) {
            false
        }
    }


}





