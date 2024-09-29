package auth.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import auth.controller_presenter.factory.UiFactory
import auth.ui.common.SnackNProgressBarDecorator
import auth.ui.login.LoginScreen
import auth.ui.register.RegisterDestination

/**
 * - It has own navigation system and ViewModel
 * - It has the route of Login,Register,Account Verification and password
 */

@Composable
fun AuthRoute(
    onLoginSuccess: (token:String) -> Unit,
) {
    val navController: NavHostController = rememberNavController()

    /**
     * Creating view model here instead of in the UiFactory because we want keep Factory framework/library independent as much as possible
     * but the view model is framework dependent
     */

    val authViewModel = viewModel {
        AuthViewModel(
            loginController = UiFactory.createLoginController(),
            registerController = UiFactory.createRegisterController()
        )
    }

    SnackNProgressBarDecorator(
        isLoading = authViewModel.isLoading.collectAsState(false).value,
        snackBarMessage = authViewModel.screenMessage.collectAsState(null).value
    ) {
        NavHost(
            navController = navController,
            startDestination = Route.LOGIN_SCREEN,
            modifier = Modifier
        ) {

            composable(route = Route.LOGIN_SCREEN) {
                LoginScreen(
                    controller = authViewModel.loginController,
                    navigateToRegisterRequest = {
                        try {
                            navController.navigate(Route.REGISTER_SCREEN)
                        } catch (_: Exception) {

                        }

                    },
                    onLoginSuccess = onLoginSuccess
                )
            }
            composable(route = Route.REGISTER_SCREEN) {

                RegisterDestination(
                    controller = authViewModel.registerController,
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


