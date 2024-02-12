package com.just.cse.digital_diary.two_zero_two_three.features.others.destination.graph

import androidx.compose.runtime.collectAsState
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.just.cse.digital_diary.two_zero_two_three.features.others.destination.other.TopBarDecorator
import com.just.cse.digital_diary.two_zero_two_three.features.others.screens.AboutUsDestination
import com.just.cse.digital_diary.two_zero_two_three.features.others.screens.EventGalleryDestination
import com.just.cse.digital_diary.two_zero_two_three.features.others.screens.HomeScreen
import com.just.cse.digital_diary.two_zero_two_three.features.others.screens.MessageFromVCDestination
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

/**
 * These are top level destination that is why we do not need separate nav host
 * This graph will be used with the drawer or the nav rails
 *
 */

object OtherFeatureNavGraph {
    const val ROUTE = "OtherFeatureNavigation"
    private const val HOME_SCREEN = "Home"
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
    fun disableBackNavigation(){
        showNavigationIcon.update { false }
    }

    fun navigateToHome(navController: NavHostController) {
        navigateAsTopMostDestination(HOME_SCREEN,navController)
    }

    fun navigateToAboutUs(navController: NavHostController) {
        navigateAsTopMostDestination(ABOUT_US_SCREEN,navController)
    }

    fun navigateToEventGallery(navController: NavHostController) {
        navigateAsTopMostDestination(EVENT_GALLERY_SCREEN,navController)
    }

    fun navigateToMessageFromVC(navController: NavHostController) {
        navigateAsTopMostDestination(MESSAGE_FROM_VC_SCREEN,navController)
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

    fun graph(navGraphBuilder: NavGraphBuilder, onExitRequest: () -> Unit = {}) {
        with(navGraphBuilder) {
            navigation(
                route = ROUTE,
                startDestination = HOME_SCREEN
            ) {
                composable(route = HOME_SCREEN) {
                    TopBarDecorator(
                        enableNavigation = showNavigationIcon.collectAsState().value,
                        onExitRequest = onExitRequest,
                        title = "Home"
                    ) {
                        HomeScreen()
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

