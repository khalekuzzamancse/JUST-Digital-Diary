package navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import auth.di.AuthComponentProvider
import auth.ui.AuthNavHost
import common.newui.Destination
import navigation.modal_drawer.TopmostNavigationHost
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import miscellaneous.ui.OtherFeatureNavGraph
import navigation.modal_drawer.NavDestination
import navigation.themes.AppTheme


private var showSlapScreen by mutableStateOf(true)

@Composable
fun AndroidRootNavigation(
    onEvent: (AppEvent) -> Unit,
){
    AppTheme {
        _AndroidRootNavigation(onEvent)
    }

}
@Composable
private fun _AndroidRootNavigation(
    onEvent: (AppEvent) -> Unit,
) {
    var navHostController = rememberNavController()
    LaunchedEffect(Unit) {
        delay(3500)
        showSlapScreen = false
    }

    val scope = rememberCoroutineScope()
    val isSignedIn = AuthComponentProvider.isSingedIn.collectAsState().value
            || AuthComponentProvider.observeSignIn().collectAsState(true).value
    val onLogOutRequest: () -> Unit = {
        AuthComponentProvider.signInOut()
    }
    LaunchedEffect(isSignedIn) {
        if (isSignedIn) {
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

    if (showSlapScreen) {
        SplashScreen()
    } else if (!showSlapScreen && isSignedIn) {
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
    } else if (!showSlapScreen && !isSignedIn) {
        AuthNavHost(
            onLoginSuccess = { username, password ->
                scope.launch {
                    AuthComponentProvider.saveSignInInfo(username, password)
                }

            }
        )
    }

}

private fun navigator(
    navController: NavHostController,
    destination: Destination,
    onEvent: (AppEvent) -> Unit,
) {
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
