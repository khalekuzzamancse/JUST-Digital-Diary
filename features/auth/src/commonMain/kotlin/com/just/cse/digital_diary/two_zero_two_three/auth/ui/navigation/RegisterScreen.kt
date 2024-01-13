package com.just.cse.digital_diary.two_zero_two_three.auth.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import cafe.adriel.voyager.core.screen.Screen
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.register.RegisterScreenCom
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.register.RegisterSection
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.register.RegistrationViewModel

class RegisterScreen(
   private val onRegisterSuccess:()->Unit,
): Screen {
    @Composable
    override fun Content() {
        RegisterScreenCom(
            onRegisterSuccess = onRegisterSuccess
        )
    }

}