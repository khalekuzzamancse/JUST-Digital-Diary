package com.just.cse.digital_diary.two_zero_two_three.auth.ui.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator



class NavGraph(
    val onLoginSuccess: () -> Unit,
) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        Navigator(LoginScreen(
            onLoginSuccess = {
                onLoginSuccess()
            },
            onNavigateToRegisterScreen = {
                navigator?.push(
                    RegisterScreen(
                        onRegisterSuccess = {
                            navigator.pop()
                        },
                        onExitRequest = {
                            navigator.pop()
                        }
                    )
                )
            }
        ))

    }

}
