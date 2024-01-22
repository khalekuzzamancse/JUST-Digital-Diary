package com.just.cse.digitaldiaryremake.justdigitaldiary.twozeotwothree.navigation

import androidx.navigation.NavController
import com.just.cse.digital_diary.two_zero_two_three.root_home.navgraph.local_destination_graph.Destination
import com.just.cse.digital_diary.two_zero_two_three.root_home.navgraph.local_destination_graph.RootModuleDrawerDestinations

class Navigator(
    private val navHostController: NavController
) {
    fun navigateToDrawerDestination(destination:Destination){
        try {
            navHostController.navigate(destination.route) {
                popUpTo(RootModuleDrawerDestinations.HOME.route) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        } catch (_: Exception) {

        }
    }
}