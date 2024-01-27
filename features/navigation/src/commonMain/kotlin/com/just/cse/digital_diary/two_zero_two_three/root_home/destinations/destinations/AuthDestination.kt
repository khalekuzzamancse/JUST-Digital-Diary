package com.just.cse.digital_diary.two_zero_two_three.root_home.destinations.destinations

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.two_zero_two_three.auth.AuthModuleEntryPoint
import com.just.cse.digital_diary.two_zero_two_three.auth.event.AuthEvent

@Composable
internal fun AuthDestination(
    onEvent:(AuthEvent) -> Unit
) {
    AuthModuleEntryPoint(
        onEvent=onEvent
    )
}