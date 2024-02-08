package com.just.cse.digital_diary.two_zero_two_three.features.others.destination.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.just.cse.digital_diary.two_zero_two_three.features.others.screens.AboutUsDestination
import com.just.cse.digital_diary.two_zero_two_three.features.others.screens.EventGalleryDestination
import com.just.cse.digital_diary.two_zero_two_three.features.others.screens.HomeScreen
import com.just.cse.digital_diary.two_zero_two_three.features.others.screens.MessageFromVCDestination

/**
 * These are top level destination that is why we do not need separate nav host
 * This graph will be used with the drawer or the nav rails
 *
 *

 */

object OtherFeatureNavGraph {
    const val ROUTE = "OtherFeatureNavigation"
    private const val HOME_SCREEN = "Home"
    private const val ABOUT_US_SCREEN = "AboutUs"
    private const val EVENT_GALLERY_SCREEN = "EventGallery"
    private const val MESSAGE_FROM_VC_SCREEN = "MessageFromVC"

    fun navigateToHome( navController: NavHostController){
        navController.navigate(HOME_SCREEN)
    }
    fun navigateToAboutUs( navController: NavHostController){
        navController.navigate(ABOUT_US_SCREEN)
    }
    fun navigateToEventGallery( navController: NavHostController){
        navController.navigate(EVENT_GALLERY_SCREEN)
    }
    fun navigateToMessageFromVC( navController: NavHostController){
        navController.navigate(MESSAGE_FROM_VC_SCREEN)
    }

    fun graph(navGraphBuilder: NavGraphBuilder) {
        with(navGraphBuilder) {
            navigation(
                route = ROUTE,
                startDestination = HOME_SCREEN
            ) {
                composable(route = HOME_SCREEN) {
                   HomeScreen()
                }
                composable(route = ABOUT_US_SCREEN) {
                    AboutUsDestination()
                }
                composable(route = EVENT_GALLERY_SCREEN) {
                    EventGalleryDestination()
                }
                composable(route=MESSAGE_FROM_VC_SCREEN){
                    MessageFromVCDestination()
                }
            }


        }

    }
}

