package com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.auth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import com.just.cse.digital_diary.two_zero_two_three.common_ui.WindowSizeDecorator

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
    WindowSizeDecorator(
        showProgressBar = viewModel.showProcessBar.collectAsState().value,
        snackBarMessage = viewModel.screenMessage.collectAsState().value,
        onCompact = {
            AuthOnNonExpanded(
                openRegistrationForm = openRequestFrom,
                event = event,
                registrationFormManager = registrationFormManager,
                loginFormManager = loginFormManager
            )
        },
        onMedium = {
            AuthOnNonExpanded(
                openRegistrationForm = openRequestFrom,
                event = event,
                registrationFormManager = registrationFormManager,
                loginFormManager = loginFormManager
            )
        },
        onExpanded = {
            AuthOnExpanded(
                openRegistrationForm = openRequestFrom,
                event = event,
                registrationFormManager = registrationFormManager,
                loginFormManager = loginFormManager
            )
        }
    )


}

