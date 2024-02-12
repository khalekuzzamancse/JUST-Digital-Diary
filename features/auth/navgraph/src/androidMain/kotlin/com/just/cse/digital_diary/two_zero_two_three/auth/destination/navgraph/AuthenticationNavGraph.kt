package com.just.cse.digital_diary.two_zero_two_three.auth.destination.navgraph

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.just.cse.digital_diary.two_zero_two_three.auth.destination.screens.AuthRoute
import com.just.cse.digitaldiary.twozerotwothree.core.di.auth.AuthComponentProvider
import kotlinx.coroutines.launch

/**
 * Using Separate navHost so that for multiple screen size refactor the navigation without
 * affecting the client code.
 * * As per the Compose Framework each NavHost must have a separate NavController,this graph need a
 * separate controller.
 *  * Pass the controller from outside so the in the configuration changes or composition the controller
 *  is not destroyed because if the controller destroyed then the navigation will the restore again
 *

 */
object AuthenticationNavGraph {
    private const val LOGIN_SCREEN = "LoginScreen"

    @Composable
    fun Graph(
        navController: NavHostController = rememberNavController(),
    ) {
        val scope = rememberCoroutineScope()
        NavHost(
            navController = navController,
            startDestination = LOGIN_SCREEN
        ) {
            composable(route = LOGIN_SCREEN) {
                AuthRoute(
                    onExitRequest = {},
                    onLoginSuccess = { username, password ->
                        scope.launch {
                            AuthComponentProvider.saveSignInInfo(username, password)
                        }

                    },
                    backHandler = { onBackButtonPress ->
                        BackHandler(onBack = onBackButtonPress)

                    }
                )
            }

        }


    }

}

