package navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import common.newui.Destination
import miscellaneous.ui.OtherFeatureNavGraph

class MainNavGraphController(
    private val navController: NavHostController,
    private val onEvent: (AppEvent) -> Unit,
) {
    internal fun navigator(destination: Destination) {
        when (destination) {
            NavDestination.Home -> {
                OtherFeatureNavGraph.navigateToHome(navController)
            }
            NavDestination.MessageFromVC -> {
                OtherFeatureNavGraph.navigateToMessageFromVC(navController)
            }
            NavDestination.AboutUs -> {
                OtherFeatureNavGraph.navigateToAboutUs(navController)
            }
            NavDestination.EventGallery -> {
                OtherFeatureNavGraph.navigateToEventGallery(navController)
            }
            NavDestination.FacultyList -> {
                navigateAsTopMostDestination(GraphRoutes.FACULTY_FEATURE, navController)
            }
            NavDestination.AdminOffice -> {
                navigateAsTopMostDestination(
                    GraphRoutes.ADMIN_OFFICE_FEATURE,
                    navController
                )
            }
            NavDestination.Search -> {
                navigateAsTopMostDestination(GraphRoutes.SEARCH, navController)
            }
            NavDestination.NoteBook -> {
                navigateAsTopMostDestination(GraphRoutes.NOTES_FEATURE, navController)
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
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }


}
