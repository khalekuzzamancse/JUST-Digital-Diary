package com.just.cse.digital_diary.two_zero_two_three.auth

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.auth.AuthScreen
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.theme.AuthModuleTheme

/**
 * * This is the Only Entry and Exit point to the AuthModule.
 * * This Composable delegate to to AuthScreen [AuthScreen]
 * @param onLoginSuccess (mandatory) will be called when login is successful
 * @param onExitRequest(mandatory) will be called when want to exit from the AuthModule
 */

@Composable
fun AuthModuleEntryPoint(
    onLoginSuccess: () -> Unit,
    onExitRequest: ()->Unit
) {
    AuthModuleTheme {
        AuthScreen(
            onLoginSuccess=onLoginSuccess,
            onExitRequest = onExitRequest
        )

    }

}

