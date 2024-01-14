package com.just.cse.digital_diary.two_zero_two_three.auth.ui.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.theme.AuthModuleTheme

@Composable
fun AuthModuleEntryPoint(
    onLoginSuccess: () -> Unit = {},
) {
    AuthModuleTheme {
        Navigator(
            NavGraph(
                onLoginSuccess = onLoginSuccess
            )
        )
    }

}