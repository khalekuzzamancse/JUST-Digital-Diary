package com.just.cse.digital_diary.two_zero_two_three.auth

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.navigation.NavGraph
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
        ) {
            SlideTransition(it)
        }

    }

}

