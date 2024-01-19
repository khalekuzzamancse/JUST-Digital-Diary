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
import com.just.cse.digital_diary.two_zero_two_three.common_ui.layout.two_panes.TwoPaneProps

@Composable
fun AuthScreen(
    onLoginSuccess: () -> Unit
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
        showPane2 = openRequestFrom,
        props = TwoPaneProps(
            pane1FillMaxWidth = true
        ),
        pane1 = {
            LoginDestination(
                data = loginFormManager.data.collectAsState().value,
                event = loginFormManager.event,
                onRegistrationFormRequest = event.onRegistrationFromOpenRequest,
                onLoginRequest = event.onLoginRequest
            )
        },
        pane2 = {
            if(registrationFormManager!=null){
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

