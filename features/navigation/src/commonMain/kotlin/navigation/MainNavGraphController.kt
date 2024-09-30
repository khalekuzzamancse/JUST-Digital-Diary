package navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import common.newui.Destination
import navigation.component.NavDestination

class MainNavGraphController(
    private val navController: NavHostController,
    private val onEvent: (AppEvent) -> Unit,
) {
    internal fun navigator(destination: Destination) {
        when (destination) {

            NavDestination.FacultyList -> {
                navigateAsTopMostDestination(GraphRoutes.FACULTY_FEATURE, navController)
            }
            NavDestination.AdminOffice -> {
                navController.navigate(GraphRoutes.ADMIN_OFFICE_FEATURE)
            }
            NavDestination.Search -> {
                navigateAsTopMostDestination(GraphRoutes.SEARCH, navController)
            }
            NavDestination.NoteBook -> {
                navigateAsTopMostDestination(GraphRoutes.NOTES_FEATURE, navController)
            }
            NavDestination.ClassSchedule -> {
                navigateAsTopMostDestination(GraphRoutes.CLASS_SCHEDULE_VIEWER, navController)
            }
            NavDestination.ExamSchedule -> {
                navigateAsTopMostDestination(GraphRoutes.EXAM_SCHEDULE_VIEWER, navController)
            }
            NavDestination.ExploreJust -> {
                onEvent(AppEvent.WebVisitRequest("https://just.edu.bd/"))
            }
            else -> {}
        }
    }
    fun pop(){
        navController.popBackStack()
    }

    private fun navigateAsTopMostDestination(
        destination: String,
        navController: NavHostController
    ) {
        navController.navigate(destination) {
            navController.graph.findStartDestination().route?.let {
                popUpTo(it) {
                    saveState = true
                }
            }
            launchSingleTop = true
            restoreState = true
        }
    }


}
