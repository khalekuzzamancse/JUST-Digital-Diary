package com.just.cse.digital_diary.two_zero_two_three.auth.ui.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.login.RegisterSection

class RegisterScreen(
   private val onRegisterSuccess:()->Unit,
): Screen {
    @Composable
    override fun Content() {
        RegisterSection(
            onRegister = onRegisterSuccess
        )
    }

}