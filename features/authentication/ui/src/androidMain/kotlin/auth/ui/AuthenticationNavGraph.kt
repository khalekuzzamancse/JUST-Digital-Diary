package auth.ui

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import auth.ui.route._AuthRoute
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
private const val LOGIN_SCREEN = "LoginScreen"

@Composable
fun AuthNavHost() {
    val navController: NavHostController = rememberNavController()
    val scope = rememberCoroutineScope()
    NavHost(
        navController = navController,
        startDestination = LOGIN_SCREEN
    ) {
        composable(route = LOGIN_SCREEN) {
            _AuthRoute(
                onExitRequest = {},
                onLoginSuccess = { username, password ->
                    scope.launch {
                        //  AuthComponentProvider.saveSignInInfo(username, password)
                    }

                },
                backHandler = { onBackButtonPress ->
                    BackHandler(onBack = onBackButtonPress)

                }
            )
        }

    }


}



