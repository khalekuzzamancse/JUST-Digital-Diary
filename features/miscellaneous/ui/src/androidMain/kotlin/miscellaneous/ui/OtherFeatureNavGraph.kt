package miscellaneous.ui

import androidx.compose.runtime.collectAsState
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import miscellaneous.navgraph.route.AboutUsDestination
import miscellaneous.navgraph.route.EventGalleryDestination
import miscellaneous.navgraph.route.HomeScreen
import miscellaneous.navgraph.route.MessageFromVCDestination
import miscellaneous.ui.route.OtherFeatureFunctionalityEvent

/**
 * These are top level destination that is why we do not need separate nav host
 * This graph will be used with the drawer or the nav rails
 *
 */

object OtherFeatureNavGraph {
    const val ROUTE = "OtherFeatureNavigation"
    const val HOME_ROUTE = "Home"
    private const val ABOUT_US_SCREEN = "AboutUs"
    private const val EVENT_GALLERY_SCREEN = "EventGallery"
    private const val MESSAGE_FROM_VC_SCREEN = "MessageFromVC"
    private val showNavigationIcon = MutableStateFlow(true)

    /**
    Useful for nav rail
     */
    fun enableBackNavigation() {
        showNavigationIcon.update { true }
    }

    fun disableBackNavigation() {
        showNavigationIcon.update { false }
    }

    fun navigateToHome(navController: NavHostController) {
        navigateAsTopMostDestination(HOME_ROUTE, navController)
    }

    fun navigateToAboutUs(navController: NavHostController) {
        navigateAsTopMostDestination(ABOUT_US_SCREEN, navController)
    }

    fun navigateToEventGallery(navController: NavHostController) {
        navigateAsTopMostDestination(EVENT_GALLERY_SCREEN, navController)
    }

    fun navigateToMessageFromVC(navController: NavHostController) {
        navigateAsTopMostDestination(MESSAGE_FROM_VC_SCREEN, navController)
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

    fun graph(
        navGraphBuilder: NavGraphBuilder, onExitRequest: () -> Unit = {},
        onEvent: (OtherFeatureEvent) -> Unit,
    ) {
        with(navGraphBuilder) {
            navigation(
                route = ROUTE,
                startDestination = HOME_ROUTE
            ) {
                composable(route = HOME_ROUTE) {
                    TopBarDecorator(
                        enableNavigation = showNavigationIcon.collectAsState().value,
                        onExitRequest = onExitRequest,
                        title = "Home"
                    ) {
                        HomeScreen(
                            onEvent = {event->
                                when(event){
                                    is OtherFeatureFunctionalityEvent.CalenderRequest->onEvent(
                                        OtherFeatureEvent.CalenderRequest(event.url)
                                    )
                                }
                            }

                        )
                    }


                }
                composable(route = ABOUT_US_SCREEN) {
                    TopBarDecorator(
                        enableNavigation = showNavigationIcon.collectAsState().value,
                        onExitRequest = onExitRequest,
                        title = "About Us"
                    ) {
                        AboutUsDestination()
                    }

                }
                composable(route = EVENT_GALLERY_SCREEN) {
                    TopBarDecorator(
                        enableNavigation = showNavigationIcon.collectAsState().value,
                        onExitRequest = onExitRequest,
                        title = "Event Gallery"
                    ) {
                        EventGalleryDestination()
                    }

                }
                composable(route = MESSAGE_FROM_VC_SCREEN) {
                    TopBarDecorator(
                        enableNavigation = showNavigationIcon.collectAsState().value,
                        onExitRequest = onExitRequest,
                        title = "Message From VC"
                    ) {
                        MessageFromVCDestination()
                    }

                }
            }
        }
    }

}

