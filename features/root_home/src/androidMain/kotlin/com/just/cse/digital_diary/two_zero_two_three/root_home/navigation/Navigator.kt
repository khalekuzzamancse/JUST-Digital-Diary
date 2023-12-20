package com.just.cse.digital_diary.two_zero_two_three.root_home.navigation

import androidx.navigation.NavController
import com.just.cse.digital_diary.two_zero_two_three.root_home.navgraph.destination_provider.Destination
import com.just.cse.digital_diary.two_zero_two_three.root_home.navgraph.destination_provider.TopMostDestinations

class Navigator(
    private val navHostController: NavController
) {
    fun navigateWithReplacementLastDestination(destination:Destination){
        try {
            navHostController.navigate(destination.route) {
                popUpTo(TopMostDestinations.HOME.route) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        } catch (_: Exception) {

        }
    }
    fun navigatePushing(destination:Destination){
        try {
            navHostController.navigate(destination.route) {
//                popUpTo(RootModuleDrawerDestinations.HOME.route) {
//                    saveState = true
//                }
                launchSingleTop = true
                restoreState = true
            }
        } catch (_: Exception) {

        }
    }
}