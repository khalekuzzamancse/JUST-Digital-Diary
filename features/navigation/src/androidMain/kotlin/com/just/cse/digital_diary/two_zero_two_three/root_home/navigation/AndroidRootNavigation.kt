package com.just.cse.digital_diary.two_zero_two_three.root_home.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.just.cse.digital_diary.two_zero_two_three.features.others.destination.graph.OtherFeatureNavGraph
import com.just.cse.digital_diary.two_zero_two_three.root_home.AppEvent
import com.just.cse.digital_diary.two_zero_two_three.root_home.modal_drawer.ModalDrawerHandler
import com.just.cse.digital_diary.two_zero_two_three.root_home.modal_drawer.RootModuleDrawer
import com.just.cse.digital_diary.two_zero_two_three.root_home.modal_drawer.TopMostDestination

private val drawerHandler = ModalDrawerHandler()

@Composable
fun AndroidRootNavigation(
    handler: ModalDrawerHandler = drawerHandler,
    onEvent: (AppEvent) -> Unit,
) {
    val navHostController = rememberNavController()
    var notSignedIn by remember { mutableStateOf(false) }
    val onLoginSuccess: (String, String) -> Unit = { _, _ ->
        notSignedIn = false
        navHostController.popBackStack()
    }
    val pop:()->Unit={
        navHostController.popBackStack()
    }
    val onLogOutRequest: () -> Unit = {
        navHostController.navigate(GraphRoutes.AUTH)
        notSignedIn = true
    }
    if (notSignedIn)
        navHostController.navigate(GraphRoutes.AUTH)
    else {
        RootModuleDrawer(
            onLogOutRequest = onLogOutRequest,
            drawerHandler = handler,
            onEvent = { destination ->
                when (destination) {
                    TopMostDestination.Home -> {
                        OtherFeatureNavGraph.navigateToHome(navHostController)
                    }

                    TopMostDestination.MessageFromVC -> {
                        OtherFeatureNavGraph.navigateToMessageFromVC(navHostController)
                    }

                    TopMostDestination.AboutUs -> {
                        OtherFeatureNavGraph.navigateToAboutUs(navHostController)
                    }

                    TopMostDestination.EventGallery -> {
                        OtherFeatureNavGraph.navigateToEventGallery(navHostController)
                    }

                    TopMostDestination.FacultyList -> {
                        navigateAsTopMostDestination(GraphRoutes.FACULTY_FEATURE,navHostController)
                       // navHostController.navigate()
                    }

                    TopMostDestination.AdminOffice -> {
                        navigateAsTopMostDestination(GraphRoutes.ADMIN_OFFICE_FEATURE,navHostController)
                      //  navHostController.navigate(GraphRoutes.ADMIN_OFFICE_FEATURE)
                    }

                    TopMostDestination.Search -> {
                        navigateAsTopMostDestination(GraphRoutes.SEARCH,navHostController)
                       // navHostController.navigate(GraphRoutes.SEARCH)
                    }

                    TopMostDestination.NoteList -> {
                        navigateAsTopMostDestination(GraphRoutes.NOTES_FEATURE,navHostController)
                        //navHostController.navigate(GraphRoutes.NOTES_FEATURE)
                    }

                    TopMostDestination.ExploreJust -> {
                        onEvent(AppEvent.WebVisitRequest("https://just.edu.bd/"))
                    }

                    else -> {}
                }
            },
            navHost = {
                RootNavGraph(
                    onEvent = onEvent,
                    openDrawerRequest = handler::openDrawer,
                    navController = navHostController,
                    onLoginSuccess = onLoginSuccess,
                    onBackPressed = pop
                )
            }
        )

    }


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