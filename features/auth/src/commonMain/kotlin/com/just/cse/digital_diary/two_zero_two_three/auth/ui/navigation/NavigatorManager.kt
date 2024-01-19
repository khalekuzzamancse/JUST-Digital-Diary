package com.just.cse.digital_diary.two_zero_two_three.auth.ui.navigation

import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator

internal class NavigatorManager(
    private val navigator: Navigator?
) {
    companion object {
        private const val TAG = "RootHomeModule_NavigatorManager ::"
        private fun log(message: String) {
            println("$TAG$message")
        }
    }

    private fun pop() {
        if (navigator == null)
            log("Can not Pop()")
        else
            navigator.pop()

    }
    private fun push(des: Screen) {
        if (navigator == null)
            log("push()->can not push")
        else
            navigator.push(des)

    }

    fun navigateLoginDestination(
        onLoginSuccess:()->Unit,
    ) {
        push(
            AuthScreen(
                onLoginSuccess = {
                    onLoginSuccess()
                },
                onNavigateToRegisterScreen ={}
            )
        )
    }



}
/*
Maintain separate navigation and backstack so that for the faculty module
so that when use click on the next back button he did not go exit from the app.
in future requirements may added...
 */

