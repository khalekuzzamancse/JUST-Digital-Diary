package com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.auth

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.login.LoginDestination
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.registration.RegisterDestination
import com.just.cse.digital_diary.two_zero_two_three.common_ui.layout.TwoPaneLayout
import com.just.cse.digital_diary.two_zero_two_three.common_ui.layout.two_panes.CompactModeTopPaneAnimation
import com.just.cse.digital_diary.two_zero_two_three.common_ui.layout.two_panes.TwoPaneProps

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
internal fun AuthScreen(
    modifier: Modifier = Modifier,
    onLoginSuccess: () -> Unit,
    onExitRequest: () -> Unit,
) {
    val viewModel = remember {
        AuthViewModel(
            onLoginSuccess = onLoginSuccess
        )
    }
    val event = viewModel.event
    val openRequestFrom = viewModel.registrationDestinationOpened.collectAsState().value
    val registrationFormManager = viewModel.registrationFormManager
    val loginFormManager = viewModel.loginFormManager


    TwoPaneLayout(
        showProgressBar = viewModel.showProcessBar.collectAsState().value,
        snackBarMessage = viewModel.screenMessage.collectAsState().value,
        showTopOrRightPane = openRequestFrom,
        secondaryPaneAnimationState = openRequestFrom,
        props = TwoPaneProps(
            pane1FillMaxWidth = true,
            topPaneAnimation = CompactModeTopPaneAnimation()
        ),
        leftPane = {
            LoginDestination(
                data = loginFormManager.data.collectAsState().value,
                event = loginFormManager.event,
                onRegistrationFormRequest = event.onRegistrationFromOpenRequest,
                onLoginRequest = event.onLoginRequest
            )
        },
        topOrRightPane = {

                if (registrationFormManager != null) {
                    RegisterDestination(
                        modifier = Modifier.verticalScroll(rememberScrollState()),
                        onExitRequest = event.onRegistrationFromCloseRequest,
                        data = registrationFormManager.data.collectAsState().value,
                        event = registrationFormManager.event,
                        onRegisterRequest = event.onRegistrationRequest,
                    )
                }


        }
    )


}

