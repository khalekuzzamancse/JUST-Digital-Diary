package auth.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import auth.ui.login.LoginDestination
import auth.ui.register.RegisterDestination

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
fun AuthRoute(
    onLoginSuccess: (String, String) -> Unit,
) {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Route.LOGIN_SCREEN
    ) {

        composable(route = Route.LOGIN_SCREEN) {
            LoginDestination(
                onNavigateToRegisterRequest = {
                    try {
                        navController.navigate(Route.REGISTER_SCREEN)
                    } catch (_: Exception) {

                    }

                }
            )
        }
        composable(route = Route.REGISTER_SCREEN) {

            RegisterDestination(
                onLoginRequest = {
                    try {
                        navController.popBackStack()
                    } catch (_: Exception) {

                    }
                }
            )
        }

    }


}

private object Route {
    const val LOGIN_SCREEN = "LoginScreen"
    const val REGISTER_SCREEN = "RegisterScreen"
}


