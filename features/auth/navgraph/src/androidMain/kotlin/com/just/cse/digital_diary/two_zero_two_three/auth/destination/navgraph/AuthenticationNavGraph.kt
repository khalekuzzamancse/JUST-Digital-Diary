package com.just.cse.digital_diary.two_zero_two_three.auth.destination.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.just.cse.digital_diary.two_zero_two_three.auth.destination.screens.AuthScreen

/**
 * Using Separate navHost so that for multiple screen size refactor the navigation without
 * affecting the client code.
 * * As per the Compose Framework each NavHost must have a separate NavController,this graph need a
 * separate controller.
 *  * Pass the controller from outside so the in the configuration changes or composition the controller
 *  is not destroyed because if the controller destroyed then the navigation will the restore again
 *

 */

@Composable
fun AuthenticationNavGraph(
    navController: NavHostController= rememberNavController()
) {
    NavHost(
        navController=navController,
        startDestination = Graph.LOGIN_SCREEN
    ){
        composable(route=Graph.LOGIN_SCREEN){
            AuthScreen(
                onExitRequest = {},
                onLoginSuccess = {username,password->

                }
            )
        }

    }


}
private  object Graph {
    const val LOGIN_SCREEN="LoginScreen"
}