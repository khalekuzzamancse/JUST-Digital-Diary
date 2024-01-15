package com.just.cse.digital_diary.two_zero_two_three.auth.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition


class NavGraph(
    val onLoginSuccess: () -> Unit,
) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        val navigatorManager= remember { NavigatorManager(navigator) }
        LaunchedEffect(Unit){//to avoid executing multiple times
            navigatorManager.navigateLoginDestination(
                onLoginSuccess=onLoginSuccess
            )
        }

    }

}

