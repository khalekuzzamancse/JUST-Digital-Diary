package com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.auth

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideOut
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.login.LoginDestination
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.login.form.LoginFormManager
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.registration.RegisterDestination
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.registration.form.RegistrationFormManager

@Composable
fun AuthOnNonExpanded(
    openRegistrationForm: Boolean,
    registrationFormManager: RegistrationFormManager? = null,
    loginFormManager: LoginFormManager,
    event: AuthScreenEvent,
) {
    Box(Modifier.fillMaxSize()) {
        LoginDestination(
            data = loginFormManager.data.collectAsState().value,
            event = loginFormManager.event,
            onRegistrationFormRequest = event.onRegistrationFromOpenRequest,
            onLoginRequest = event.onLoginRequest
        )
        AnimatedVisibility(
            visible = openRegistrationForm && registrationFormManager != null,
        ){
            if (registrationFormManager!=null){
                RegisterDestination(
                    modifier = Modifier.verticalScroll(rememberScrollState()),
                    onExitRequest = event.onRegistrationFromCloseRequest,
                    data = registrationFormManager.data.collectAsState().value,
                    event = registrationFormManager.event,
                    onRegisterRequest = event.onRegistrationRequest,
                )
            }

        }


    }

}