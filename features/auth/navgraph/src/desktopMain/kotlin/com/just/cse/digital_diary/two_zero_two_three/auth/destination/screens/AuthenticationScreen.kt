package com.just.cse.digital_diary.two_zero_two_three.auth.destination.screens

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen

internal class AuthenticationScreen : Screen {
    @Composable
    override fun Content() {
        AuthScreen(onLoginSuccess = { _, _ ->
        }, onExitRequest = {})
    }
}