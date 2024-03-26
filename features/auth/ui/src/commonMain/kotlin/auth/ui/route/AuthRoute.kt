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
import auth.di.AuthComponentProvider
import auth.domain.login.repoisitory.LoginRepository
import auth.domain.register.repository.RegisterRepository
import auth.ui.login.components.event.LoginEvent
import auth.ui.login.route.LoginDestination
import auth.ui.login.route.LoginDestinationViewModel
import auth.ui.register.components.events.RegisterControlEvents
import auth.ui.register.route.RegisterDestination
import auth.ui.register.route.RegisterDestinationViewModel
import common.newui.SnackNProgressBarDecorator
import common.newui.TwoPaneLayout
import common.newui.TwoPaneNewUIPros
import kotlinx.coroutines.launch

/**
 * * This is the Only Entry and Exit point to the AuthModule.
 * * This Composable delegate to to AuthScreen [_AuthRoute]
 * @param onLoginSuccess (mandatory) will be called when login is successful
 */

@Composable
fun _AuthRoute(
    onLoginSuccess: (String, String) -> Unit,
    backHandler: @Composable (onBackButtonPress: () -> Unit) -> Unit,
) {
    _AuthRoute(
        loginRepository = AuthComponentProvider.getLoginRepository(),
        registrationRepository = AuthComponentProvider.getRegisterRepository(),
        onLoginSuccess = onLoginSuccess,
        backHandler = backHandler
    )

}

@Composable
private fun _AuthRoute(
    loginRepository: LoginRepository,
    registrationRepository: RegisterRepository,
    onLoginSuccess: (userName: String, password: String) -> Unit,
    backHandler: @Composable (onBackButtonPress: () -> Unit) -> Unit,
) {
    val authViewModel = remember {
        AuthViewModel(
            loginRepository = loginRepository,
            registrationRepository = registrationRepository
        )
    }
    _AuthRoute(
        modifier = Modifier,
        authViewModel = authViewModel,
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
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel,
    onLoginSuccess: (userName: String, password: String) -> Unit,
    backHandler: @Composable (onBackButtonPress: () -> Unit) -> Unit,
) {
    val scope = rememberCoroutineScope()
    val state = authViewModel.uiState.collectAsState().value
    _AuthRoute(
        modifier = modifier,
        backHandler = backHandler,
        onCloseRegisterFormRequest = {
            authViewModel.closeRegistrationDestination()
        },
        state = state,
        loginViewModel = authViewModel.loginViewModel,
        registerViewModel = authViewModel.registerViewModel,
        onSnackBarDetailsClosed = authViewModel::closeSnackBar,
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
    onSnackBarDetailsClosed: () -> Unit,
    onCloseRegisterFormRequest: () -> Unit,
    backHandler: @Composable (onBackButtonPress: () -> Unit) -> Unit
) {
    val snackBarData = state.snackBarData
    backHandler {
        if (state.showRegisterForm)
            onCloseRegisterFormRequest()
    }
    SnackNProgressBarDecorator(
        snackBarData = snackBarData,
        showProgressBar = state.showProgressBar,
        onSnackBarCloseRequest = onSnackBarDetailsClosed
    ) {
        _AuthRawDestination(
            modifier = modifier,
            state = state,
            loginViewModel = loginViewModel,
            registerViewModel = registerViewModel,
            onLoginEvent = onLoginEvent,
            onRegisterEvent = onRegisterEvent,
            onCloseRegisterFormRequest = onCloseRegisterFormRequest
        )
    }

}

@Composable
private fun _AuthRawDestination(
    modifier: Modifier = Modifier,
    state: AuthScreenState,
    loginViewModel: LoginDestinationViewModel,
    registerViewModel: RegisterDestinationViewModel,
    onLoginEvent: (LoginEvent) -> Unit,
    onRegisterEvent: (RegisterControlEvents) -> Unit = {},
    onCloseRegisterFormRequest: () -> Unit,
) {
    val navigationIcon = if (state.showRegisterForm) Icons.AutoMirrored.Filled.ArrowBack else null
    val alignment = Alignment.TopStart
    val props = TwoPaneNewUIPros(
        showTopOrRightPane = state.showRegisterForm,
        alignment = alignment,
        navigationIcon = navigationIcon
    )

    TwoPaneLayout(
        modifier = modifier,
        props = props,
        onNavigationIconClick = if (state.showRegisterForm) onCloseRegisterFormRequest else null,
        leftPane = {
            LoginDestination(
                modifier = Modifier.padding(8.dp)
                    .widthIn(max = 700.dp)
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState()),
                viewModel = loginViewModel,
                onEvent = onLoginEvent
            )
        },
        topOrRightPane = {
            RegisterDestination(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                viewModel = registerViewModel,
                onEvent = onRegisterEvent
            )
        },

        )

}
