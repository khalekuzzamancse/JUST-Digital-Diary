package com.just.cse.digital_diary.two_zero_two_three.root_home.destinations.child_destination

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.two_zero_two_three.auth.AuthModuleEntryPoint

@Composable
internal fun AuthDestination(
    onLoginSuccess: () -> Unit,
    onExitRequest: ()->Unit
) {
    AuthModuleEntryPoint(
        onLoginSuccess = onLoginSuccess,
        onExitRequest = onExitRequest
    )
}