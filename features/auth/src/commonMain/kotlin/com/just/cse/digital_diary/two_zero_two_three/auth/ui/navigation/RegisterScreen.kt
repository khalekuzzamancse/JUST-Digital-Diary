package com.just.cse.digital_diary.two_zero_two_three.auth.ui.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.registration.RegisterScreenCom

class RegisterScreen(
    private val onExitRequest:()->Unit,
   private val onRegisterSuccess:()->Unit,
): Screen {
    @Composable
    override fun Content() {
        RegisterScreenCom(
            onRegisterSuccess = onRegisterSuccess,
            onExitRequest = onExitRequest
        )
    }

}