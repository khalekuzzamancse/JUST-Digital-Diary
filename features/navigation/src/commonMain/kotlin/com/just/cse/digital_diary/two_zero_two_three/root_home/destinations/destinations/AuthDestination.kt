package com.just.cse.digital_diary.two_zero_two_three.root_home.destinations.destinations

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.two_zero_two_three.auth.destination.AuthDestinationEvent
import com.just.cse.digital_diary.two_zero_two_three.auth.destination.AuthModuleEntryPoint


@Composable
internal fun AuthDestination(
    onEvent:(AuthDestinationEvent) -> Unit
) {
    AuthModuleEntryPoint(
        onLoginSuccess = {
            onEvent(AuthDestinationEvent.LoginSuccess)
        }
    )
}