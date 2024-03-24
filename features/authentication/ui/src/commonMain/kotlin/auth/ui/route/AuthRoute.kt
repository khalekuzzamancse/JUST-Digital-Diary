package auth.ui.route

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import auth.di.ComponentProvider
import auth.domain.login.repoisitory.LoginRepository
import auth.domain.register.repository.RegisterRepository
import auth.ui.login.components.event.LoginEvent
import auth.ui.login.route.LoginDestination
import auth.ui.login.route.LoginDestinationViewModel
import auth.ui.register.components.events.RegisterControlEvents
import auth.ui.register.route.RegisterDestination
import auth.ui.register.route.RegisterDestinationViewModel
import com.just.cse.digital_diary.two_zero_two_three.common_ui.layout.TwoPaneLayout
import kotlinx.coroutines.launch

/**
 * * This is the Only Entry and Exit point to the AuthModule.
 * * This Composable delegate to to AuthScreen [_AuthRoute]
 * @param onLoginSuccess (mandatory) will be called when login is successful
 * @param onExitRequest(mandatory) will be called when want to exit from the AuthModule
 */

@Composable
fun _AuthRoute(
    onLoginSuccess:(String,String)->Unit,
    onExitRequest:()->Unit={},
    backHandler: @Composable (onBackButtonPress: () -> Unit) -> Unit,
) {
    _AuthRoute(
        loginRepository = ComponentProvider.getLoginRepository(),
        registrationRepository = ComponentProvider.getRegisterRepository(),
        onLoginSuccess =onLoginSuccess,
        backHandler=backHandler
    )



}

@Composable
private fun _AuthRoute(
    loginRepository: LoginRepository,
    registrationRepository: RegisterRepository,
    onLoginSuccess: (userName: String, password: String) -> Unit,
    backHandler: @Composable (onBackButtonPress: () -> Unit) -> Unit,
) {
    val authViewModel = remember{
        AuthViewModel(
            loginRepository = loginRepository,
            registrationRepository = registrationRepository
        )
    }
        _AuthRoute(
            modifier = Modifier,
            authViewModel=authViewModel,
            onLoginSuccess = onLoginSuccess,
            backHandler = backHandler
        )


}
/**
 * * This composable is only accessible within this module
 * * It serves as the container for the Login[LoginDestination] and Register[RegisterDestination] Destinations.
 * * Client don't have direct access to the Login[LoginDestination] and Register[RegisterDestination]
 * * Initially it Open the Login[LoginDestination]
 * * Upon a Register request, the Register Destination [RegisterDestination] becomes visible.
 * * In compact Window Register [RegisterDestination] destination will be pop up top of Login[LoginDestination] destination
 * * In Medium and Expanded Window Login[LoginDestination] and Register [RegisterDestination] destination are displayed side by side
 * @param modifier [Modifier] (mandatory) controls size and other behaviors of the Auth Screen
 * @param onLoginSuccess (mandatory) will be called when login is successful
 */
@Composable
private fun _AuthRoute(
    modifier: Modifier=Modifier,
    authViewModel:AuthViewModel,
    onLoginSuccess: (userName: String, password: String) -> Unit,
    backHandler: @Composable (onBackButtonPress: () -> Unit) -> Unit,
) {
    val scope = rememberCoroutineScope()
    val state = authViewModel.uiState.collectAsState().value
        _AuthRoute(
            modifier =modifier,
            backHandler = backHandler,
            onCloseRegisterFormRequest = {
                authViewModel.closeRegistrationDestination()
            },
            state = state,
            loginViewModel = authViewModel.loginViewModel,
            registerViewModel = authViewModel.registerViewModel,
            onLoginEvent = { event ->
                when (event) {
                    LoginEvent.LoginControlsEvent.RegisterRequest -> {
                        authViewModel.openRegisterDestination()
                    }

                    LoginEvent.LoginControlsEvent.LoginRequest -> {
                        scope.launch {
                            val responseModel = authViewModel.login()
                            if (responseModel != null)
                                onLoginSuccess(responseModel.username, responseModel.password)

                        }

                    }
                }
            },
            onRegisterEvent = { event ->
                when (event) {
                    RegisterControlEvents.RegisterRequest -> {
                        scope.launch {
                            val success = authViewModel.register()
                            if (success) {
                                authViewModel.closeRegistrationDestination()
                            }
                        }

                    }
                }
            },

            )


}

@Composable
private fun _AuthRoute(
    modifier: Modifier = Modifier,
    state: AuthScreenState,
    loginViewModel: LoginDestinationViewModel,
    registerViewModel: RegisterDestinationViewModel,
    onLoginEvent: (LoginEvent) -> Unit,
    onRegisterEvent: (RegisterControlEvents) -> Unit = {},
    onCloseRegisterFormRequest: () -> Unit,
    backHandler: @Composable (onBackButtonPress: () -> Unit) -> Unit
) {
    backHandler {
        if (state.showRegisterForm)
            onCloseRegisterFormRequest()
    }
    TwoPaneLayout(
        modifier = modifier,
        navigationIcon = if (state.showRegisterForm) Icons.AutoMirrored.Filled.ArrowBack else null,
        onNavigationIconClick = if (state.showRegisterForm) onCloseRegisterFormRequest else null,
        showProgressBar = state.showProgressBar,
        snackBarMessage = state.snackBarMessage,
        showTopOrRightPane = state.showRegisterForm,
        leftPane = {
            LoginDestination(
                modifier = Modifier.padding(8.dp)
                    .widthIn(max = 700.dp)
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState())
//                    .background(Color.Blue)
                ,
                viewModel = loginViewModel,
                onEvent = onLoginEvent
            )
        },
        topOrRightPane = {
            RegisterDestination(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
//                    .background(Color.Red)
                ,
                viewModel = registerViewModel,
                onEvent = onRegisterEvent
            )
        },
        alignment = Alignment.TopStart
    )
}



