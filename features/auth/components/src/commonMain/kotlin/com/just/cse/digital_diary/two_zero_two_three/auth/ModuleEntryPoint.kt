package com.just.cse.digital_diary.two_zero_two_three.auth

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.two_zero_two_three.auth.event.AuthEvent
import com.just.cse.digital_diary.two_zero_two_three.auth.theme.AuthModuleTheme

/**
 * * This is the Only Entry and Exit point to the AuthModule.
 * * This Composable delegate to to AuthScreen [AuthScreen]
 * @param onLoginSuccess (mandatory) will be called when login is successful
 * @param onExitRequest(mandatory) will be called when want to exit from the AuthModule
 */

@Composable
fun AuthModuleEntryPoint(
    onEvent:(AuthEvent) -> Unit
) {
    AuthModuleTheme {
        AuthScreen(
            onEvent=onEvent
        )

    }

}

