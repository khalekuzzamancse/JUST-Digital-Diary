package com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.auth

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.login.LoginDestination
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.login.form.LoginFormManager
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.registration.RegisterDestination
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.registration.form.RegistrationFormManager

@Composable
internal fun AuthOnExpanded(
    openRegistrationForm: Boolean,
    registrationFormManager: RegistrationFormManager? = null,
    loginFormManager: LoginFormManager,
    event: AuthScreenEvent,
) {

    val loginDestination = @Composable {
        LoginDestination(
            data = loginFormManager.data.collectAsState().value,
            event = loginFormManager.event,
            onRegistrationFormRequest = event.onRegistrationFromOpenRequest,
            onLoginRequest = event.onLoginRequest
        )
    }

    val regFormNotOpened = !openRegistrationForm
    if (regFormNotOpened) {
        loginDestination()
    } else {
        Row(modifier = Modifier.fillMaxWidth()) {
            Box(Modifier.weight(1f, false)) {
                loginDestination()
            }
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                AnimatedVisibility(
                    visible = registrationFormManager != null,
                    enter = slideInHorizontally(),
                    exit = slideOutHorizontally()
                ){
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
            }


        }
    }


}