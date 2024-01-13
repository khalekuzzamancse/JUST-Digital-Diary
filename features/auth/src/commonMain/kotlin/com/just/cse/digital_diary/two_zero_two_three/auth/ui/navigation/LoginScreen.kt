package com.just.cse.digital_diary.two_zero_two_three.auth.ui.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.login.LoginScreenCom

class LoginScreen(
    private val onNavigateToRegisterScreen: () -> Unit = {},
    private val onLoginSuccess: () -> Unit = {},
) : Screen {
    @Composable
    override fun Content() {
        LoginScreenCom(
            onLoginSuccess=onLoginSuccess,
            onNavigateToRegisterScreen=onNavigateToRegisterScreen
        )

    }

}