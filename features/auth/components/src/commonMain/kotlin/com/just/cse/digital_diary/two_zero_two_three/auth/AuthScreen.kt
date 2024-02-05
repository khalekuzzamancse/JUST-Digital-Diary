package com.just.cse.digital_diary.two_zero_two_three.auth

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.register.destination.RegisterDestination
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.register.destination.viewmodel.RegisterDestinationViewModel
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.register.events.RegisterDestinationEvent
import com.just.cse.digital_diary.two_zero_two_three.auth.state.AuthScreenState
import com.just.cse.digital_diary.two_zero_two_three.auth.viewmodel.AuthViewModel
import com.just.cse.digital_diary.two_zero_two_three.common_ui.layout.TwoPaneLayout
import com.just.cse.digital_diary.two_zero_two_three.common_ui.layout.two_panes.CompactModeTopPaneAnimation
import com.just.cse.digital_diary.two_zero_two_three.common_ui.layout.two_panes.TwoPaneProps
import com.just.cse.digital_diary.two_zero_two_three.domain.register.repository.RegisterRepository
import com.just.cse.digital_diary.two_zero_two_three.domain_layer.login.repoisitory.LoginRepository
import com.just.cse.digital_diary.two_zero_two_three.ui_layer.login.login_destination.destination.LoginDestination
import com.just.cse.digital_diary.two_zero_two_three.ui_layer.login.event.LoginModuleEvent
import com.just.cse.digital_diary.two_zero_two_three.ui_layer.login.login_destination.viewmodel.LoginDestinationViewModel
import kotlinx.coroutines.launch


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
 * @param onExitRequest(mandatory) will be called when want to exit from the AuthModule
 */
@Composable
fun AuthScreen(
    loginRepository: LoginRepository,
    registrationRepository: RegisterRepository,
    onLoginSuccess: () -> Unit,
) {
    val scope = rememberCoroutineScope()
    val authViewModel = remember {
        AuthViewModel(
            loginRepository = loginRepository,
            registrationRepository = registrationRepository
        )
    }

    val state = authViewModel.uiState.collectAsState().value

    AuthScreen(
        state = state,
        loginViewModel = authViewModel.loginViewModel,
        registerViewModel = authViewModel.registerViewModel,
        onLoginEvent = { event ->
            when (event) {
                LoginModuleEvent.LoginControlsEvent.RegisterRequest -> {
                    authViewModel.openRegisterDestination()
                }

                LoginModuleEvent.LoginControlsEvent.LoginRequest -> {
                  scope.launch {
                      val success=authViewModel.login()
                      println("onLoginRequest:$success")
                  }

                }
            }
        },
        onRegisterEvent = { event ->
            when (event) {
                RegisterDestinationEvent.RegisterControlEvents.RegisterRequest -> {}
                RegisterDestinationEvent.ExitRequest -> {
                    authViewModel.closeRegistrationDestination()
                }
            }
        },

        )

}

@Composable
private fun AuthScreen(
    state: AuthScreenState,
    loginViewModel: LoginDestinationViewModel,
    registerViewModel: RegisterDestinationViewModel,
    onLoginEvent: (LoginModuleEvent) -> Unit,
    onRegisterEvent: (RegisterDestinationEvent) -> Unit = {},
) {
    TwoPaneLayout(
        showProgressBar = state.showProgressBar,
        snackBarMessage = state.snackBarMessage,
        showTopOrRightPane = state.showRegisterForm,
        secondaryPaneAnimationState = state.showRegisterForm,
        props = TwoPaneProps(
            pane1FillMaxWidth = true,
            topPaneAnimation = CompactModeTopPaneAnimation()
        ),
        leftPane = {
            LoginDestination(
                modifier = Modifier,
                viewModel = loginViewModel,
                onEvent = onLoginEvent
            )
        },
        topOrRightPane = {
            RegisterDestination(
                modifier = Modifier.verticalScroll(rememberScrollState()),
                viewModel = registerViewModel,
                onEvent = onRegisterEvent
            )
        }
    )


}

