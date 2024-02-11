package com.just.cse.digital_diary.two_zero_two_three.auth.destination.screens

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.two_zero_two_three.auth.AuthRoute
import com.just.cse.digital_diary.two_zero_two_three.auth.theme.AuthModuleTheme
import com.just.cse.digitaldiary.twozerotwothree.core.di.auth.AuthComponentProvider

/**
 * * This is the Only Entry and Exit point to the AuthModule.
 * * This Composable delegate to to AuthScreen [AuthRoute]
 * @param onLoginSuccess (mandatory) will be called when login is successful
 * @param onExitRequest(mandatory) will be called when want to exit from the AuthModule
 */

@Composable
internal fun AuthRoute(
    onLoginSuccess:(String,String)->Unit,
    onExitRequest:()->Unit={},
    backHandler: @Composable (onBackButtonPress: () -> Unit) -> Unit,
) {
    AuthModuleTheme {
        AuthRoute(
            loginRepository = AuthComponentProvider.getLoginRepository(),
            registrationRepository = AuthComponentProvider.getRegisterRepository(),
            onLoginSuccess =onLoginSuccess,
            backHandler=backHandler
        )

    }

}

