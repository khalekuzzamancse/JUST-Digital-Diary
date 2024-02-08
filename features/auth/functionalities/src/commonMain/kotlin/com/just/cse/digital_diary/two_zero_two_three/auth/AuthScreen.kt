package com.just.cse.digital_diary.two_zero_two_three.auth

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.auth.functionalities.register.RegisterDestination
import com.just.cse.digital_diary.two_zero_two_three.auth.functionalities.register.viewmodel.RegisterDestinationViewModel
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.register.events.RegisterControlEvents
import com.just.cse.digital_diary.two_zero_two_three.auth.state.AuthScreenState
import com.just.cse.digital_diary.two_zero_two_three.auth.viewmodel.AuthViewModel
import com.just.cse.digital_diary.two_zero_two_three.common_ui.layout.TwoPaneLayout
import com.just.cse.digital_diary.two_zero_two_three.common_ui.layout.two_panes.CompactModeTopPaneAnimation
import com.just.cse.digital_diary.two_zero_two_three.common_ui.layout.two_panes.TwoPaneProps
import com.just.cse.digital_diary.two_zero_two_three.domain.register.repository.RegisterRepository
import com.just.cse.digital_diary.two_zero_two_three.domain_layer.login.repoisitory.LoginRepository
import com.just.cse.digital_diary.two_zero_two_three.auth.functionalities.login.destination.LoginDestination
import com.just.cse.digital_diary.two_zero_two_three.auth.functionalities.login.viewmodel.LoginDestinationViewModel
import com.just.cse.digital_diary.two_zero_two_three.ui_layer.login.event.LoginEvent
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
fun AuthDestination(
    loginRepository: LoginRepository,
    registrationRepository: RegisterRepository,
    onLoginSuccess: (userName:String,password:String) -> Unit,
) {
    val scope = rememberCoroutineScope()
    val authViewModel = remember {
        AuthViewModel(
            loginRepository = loginRepository,
            registrationRepository = registrationRepository
        )
    }

    val state = authViewModel.uiState.collectAsState().value

    AuthDestination(
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
                      val responseModel=authViewModel.login()
                      if (responseModel!=null)
                          onLoginSuccess(responseModel.username, responseModel.password)

                  }

                }
            }
        },
        onRegisterEvent = { event ->
            when (event) {
                RegisterControlEvents.RegisterRequest -> {
                    scope.launch {
                        val success=authViewModel.register()
                        if (success) {
                            authViewModel.closeRegistrationDestination()
                        }
                    }

                }
//                RegisterControlEvents.ExitRequest -> {
//                    authViewModel.closeRegistrationDestination()
//                }
            }
        },

        )

}

@Composable
private fun AuthDestination(
    state: AuthScreenState,
    loginViewModel: LoginDestinationViewModel,
    registerViewModel: RegisterDestinationViewModel,
    onLoginEvent: (LoginEvent) -> Unit,
    onRegisterEvent: (RegisterControlEvents) -> Unit = {},
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

