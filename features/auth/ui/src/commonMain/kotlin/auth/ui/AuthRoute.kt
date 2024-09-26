package auth.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import auth.ui.common.SnackNProgressBarDecorator
import auth.controller_presenter.factory.UiFactory
import auth.ui.login.LoginDestinationViewModel
import auth.ui.login.LoginScreen
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

    /**
     * Creating view model here instead of in the UiFactory because we want keep Factory framework/library independent as much as possible
     * but the view model is framework dependent
     */
    val viewModel = remember {
        LoginDestinationViewModel(
            loginController = UiFactory.createLoginController(),
            registerController = UiFactory.createRegisterController()
        )
    }

    SnackNProgressBarDecorator(
        isLoading = viewModel.isLoading.collectAsState(false).value,
        snackBarMessage = viewModel.screenMessage.collectAsState(null).value
    ) {
        NavHost(
            navController = navController,
            startDestination = Route.LOGIN_SCREEN,
            modifier = Modifier
        ) {

            composable(route = Route.LOGIN_SCREEN) {
                LoginScreen(
                    controller = viewModel.loginController,
                    navigateToRegisterRequest = {
                        try {
                            navController.navigate(Route.REGISTER_SCREEN)
                        } catch (_: Exception) {

                        }

                    },
                    onLoginSuccess = {

                    }
                )
            }
            composable(route = Route.REGISTER_SCREEN) {

                RegisterDestination(
                    controller = viewModel.registerController,
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


}

private object Route {
    const val LOGIN_SCREEN = "LoginScreen"
    const val REGISTER_SCREEN = "RegisterScreen"
}


