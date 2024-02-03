package com.just.cse.digital_diary.two_zero_two_three.auth.destination

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.two_zero_two_three.auth.AuthScreen
import com.just.cse.digital_diary.two_zero_two_three.auth.event.AuthEvent
import com.just.cse.digital_diary.two_zero_two_three.auth.theme.AuthModuleTheme
import com.just.cse.digitaldiary.twozerotwothree.core.di.auth.AuthComponentProvider

/**
 * * This is the Only Entry and Exit point to the AuthModule.
 * * This Composable delegate to to AuthScreen [AuthScreen]
 * @param onLoginSuccess (mandatory) will be called when login is successful
 * @param onExitRequest(mandatory) will be called when want to exit from the AuthModule
 */

@Composable
fun AuthModuleEntryPoint(
    onEvent:(AuthDestinationEvent) -> Unit
) {
    AuthModuleTheme {
        AuthScreen(
            onEvent={event->
                    when(event) {
                        AuthEvent.LoginSuccess->{onEvent(AuthDestinationEvent.LoginSuccess)}
                        AuthEvent.ExitRequest->{onEvent(AuthDestinationEvent.ExitRequest)}
                    }
            },
            loginRepository = AuthComponentProvider.getLoginRepository(),
            registrationRepository = AuthComponentProvider.getRegisterRepository()
        )

    }

}

