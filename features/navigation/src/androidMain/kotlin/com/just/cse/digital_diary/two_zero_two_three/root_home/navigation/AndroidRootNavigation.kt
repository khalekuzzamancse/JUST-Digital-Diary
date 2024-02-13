package com.just.cse.digital_diary.two_zero_two_three.root_home.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.just.cse.digital_diary.two_zero_two_three.auth.destination.navgraph.AuthNavHost
import com.just.cse.digital_diary.two_zero_two_three.features.others.destination.graph.OtherFeatureNavGraph
import com.just.cse.digital_diary.two_zero_two_three.root_home.AppEvent
import com.just.cse.digital_diary.two_zero_two_three.root_home.modal_drawer.ModalDrawerHandler
import com.just.cse.digital_diary.two_zero_two_three.root_home.modal_drawer.RootModuleDrawerAnimationLess
import com.just.cse.digital_diary.two_zero_two_three.root_home.modal_drawer.TopMostDestination
import com.just.cse.digitaldiary.twozerotwothree.core.di.auth.AuthComponentProvider
import kotlinx.coroutines.flow.collect


@Composable
fun AndroidRootNavigation(
    onEvent: (AppEvent) -> Unit,
) {
    var handler = remember {  ModalDrawerHandler() }
    var navHostController = rememberNavController()
    val isSignedIn = AuthComponentProvider.isSingedIn.collectAsState().value
            || AuthComponentProvider.observeSignIn().collectAsState(true).value
    val onLogOutRequest: () -> Unit = {
        AuthComponentProvider.signInOut()
    }

    LaunchedEffect(Unit) {
        navHostController.currentBackStackEntryFlow.collect {
            val peekRoute = it.destination.route
            if (peekRoute == OtherFeatureNavGraph.HOME_ROUTE) {
                //may be for back press,currently in the home,but the drawer item may be
                //selected has not changed to home,that is why manually chaining selection item
                //try to implement a better solution later
                handler.onSectionSelected(0)
            }
        }
    }

    val pop: () -> Unit = {
        navHostController.popBackStack()
    }
    if (isSignedIn) {
        //clear the old nav controller
        navHostController = rememberNavController()
        handler= ModalDrawerHandler()
        RootModuleDrawerAnimationLess(
            onLogOutRequest = onLogOutRequest,
            drawerHandler = handler,
            onEvent = { destination ->
                navigator(navHostController, destination, onEvent = onEvent)
            },
            navHost = {
                RootNavGraph(
                    onEvent = onEvent,
                    openDrawerRequest = handler::openDrawer,
                    navController = navHostController,
                    onBackPressed = pop,
                    startDestination = GraphRoutes.TOPMOST_OTHER_FEATURE
                )
            }
        )
    } else {
        AuthNavHost()
    }

}

private fun navigator(
    navController: NavHostController,
    destination: TopMostDestination,
    onEvent: (AppEvent) -> Unit,
) {
    when (destination) {
        TopMostDestination.Home -> {
            OtherFeatureNavGraph.navigateToHome(navController)
        }

        TopMostDestination.MessageFromVC -> {
            OtherFeatureNavGraph.navigateToMessageFromVC(navController)
        }

        TopMostDestination.AboutUs -> {
            OtherFeatureNavGraph.navigateToAboutUs(navController)
        }

        TopMostDestination.EventGallery -> {
            OtherFeatureNavGraph.navigateToEventGallery(navController)
        }

        TopMostDestination.FacultyList -> {
            navigateAsTopMostDestination(GraphRoutes.FACULTY_FEATURE, navController)
        }

        TopMostDestination.AdminOffice -> {
            navigateAsTopMostDestination(
                GraphRoutes.ADMIN_OFFICE_FEATURE,
                navController
            )
        }

        TopMostDestination.Search -> {
            navigateAsTopMostDestination(GraphRoutes.SEARCH, navController)
        }

        TopMostDestination.NoteList -> {
            navigateAsTopMostDestination(GraphRoutes.NOTES_FEATURE, navController)
        }

        TopMostDestination.ExploreJust -> {
            onEvent(AppEvent.WebVisitRequest("https://just.edu.bd/"))
        }

        else -> {}
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
