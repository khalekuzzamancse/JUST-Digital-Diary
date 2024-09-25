package auth.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import auth.factory.UiFactory
import auth.ui.login.LoginDestinationViewModel
import auth.ui.login.LoginScreen
import auth.ui.register.RegisterDestination
import kotlinx.coroutines.flow.collect

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
    val viewModel = remember { LoginDestinationViewModel(UiFactory.createLoginController()) }
    val controller = viewModel.controller
    val hostState= remember { SnackbarHostState() }
    LaunchedEffect(Unit){
        controller.errorMessage.collect{msg->
            msg?.let {
                hostState.showSnackbar(
                    message = msg
                )
            }
        }

    }

    Scaffold(
        modifier = Modifier,
        snackbarHost = {
            SnackbarHost(hostState=hostState)
        }
    ) { innerPadding->
        NavHost(
            navController = navController,
            startDestination = Route.LOGIN_SCREEN,
            modifier = Modifier.padding(innerPadding)
        ) {

            composable(route = Route.LOGIN_SCREEN) {
                LoginScreen(
                    controller = controller,
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


