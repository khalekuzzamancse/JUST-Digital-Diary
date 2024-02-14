package com.just.cse.digital_diary.two_zero_two_three.root_home.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.just.cse.digital_diary.two_zero_two_three.auth.destination.navgraph.AuthNavHost
import com.just.cse.digital_diary.two_zero_two_three.features.others.destination.graph.OtherFeatureNavGraph
import com.just.cse.digital_diary.two_zero_two_three.root_home.AppEvent
import com.just.cse.digital_diary.two_zero_two_three.root_home.modal_drawer.TopRoutes
import com.just.cse.digital_diary.two_zero_two_three.root_home.modal_drawer.TopmostNavigationHost
import com.just.cse.digitaldiary.twozerotwothree.core.di.auth.AuthComponentProvider


@Composable
fun AndroidRootNavigation(
    onEvent: (AppEvent) -> Unit,
) {
    var navHostController = rememberNavController()

    val isSignedIn = AuthComponentProvider.isSingedIn.collectAsState().value
            || AuthComponentProvider.observeSignIn().collectAsState(true).value
    val onLogOutRequest: () -> Unit = {
        AuthComponentProvider.signInOut()
    }
    LaunchedEffect(isSignedIn){
        if (isSignedIn){
            AuthComponentProvider.updateAuthToken()
        }
    }


    LaunchedEffect(Unit) {
        navHostController.currentBackStackEntryFlow.collect {
            val peekRoute = it.destination.route
            if (peekRoute == OtherFeatureNavGraph.HOME_ROUTE) {
                //may be for back press,currently in the home,but the drawer item may be
                //selected has not changed to home,that is why manually chaining selection item
                //try to implement a better solution later
                TopmostNavigationHost.selectFirst()
            }
        }
    }

    val pop: () -> Unit = {
        navHostController.popBackStack()
    }
    if (isSignedIn) {
        //clear the old nav controller
        navHostController = rememberNavController()
        TopmostNavigationHost.DrawerHost(
            onLogOutRequest = onLogOutRequest,
            onRouteSelected = { destination ->
                navigator(navHostController, destination, onEvent = onEvent)
            },
            navHost = {
                RootNavGraph(
                    onEvent = onEvent,
                    openDrawerRequest = TopmostNavigationHost::openDrawer,
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
    destination: TopRoutes.NavDestination,
    onEvent: (AppEvent) -> Unit,
) {
    when (destination) {
        TopRoutes.Home -> {
            OtherFeatureNavGraph.navigateToHome(navController)
        }

        TopRoutes.MessageFromVC -> {
            OtherFeatureNavGraph.navigateToMessageFromVC(navController)
        }

        TopRoutes.AboutUS -> {
            OtherFeatureNavGraph.navigateToAboutUs(navController)
        }

        TopRoutes.EventGallery -> {
            OtherFeatureNavGraph.navigateToEventGallery(navController)
        }

        TopRoutes.Faculties -> {
            navigateAsTopMostDestination(GraphRoutes.FACULTY_FEATURE, navController)
        }

        TopRoutes.AdminOffice -> {
            navigateAsTopMostDestination(
                GraphRoutes.ADMIN_OFFICE_FEATURE,
                navController
            )
        }

        TopRoutes.Search -> {
            navigateAsTopMostDestination(GraphRoutes.SEARCH, navController)
        }

        TopRoutes.Notes -> {
            navigateAsTopMostDestination(GraphRoutes.NOTES_FEATURE, navController)
        }

        TopRoutes.ExploreJUST -> {
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
