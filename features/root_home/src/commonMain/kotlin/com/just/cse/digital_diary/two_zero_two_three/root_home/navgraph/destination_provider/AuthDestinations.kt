package com.just.cse.digital_diary.two_zero_two_three.root_home.navgraph.destination_provider

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.two_zero_two_three.root_home.destinations.child_destination.AuthDestination

object AuthDestinations {
    val AUTH=Destination.createDestination("AUTH")
    @Composable
    fun Auth(
        onLoginSuccess:()->Unit,
        onExitRequest:()->Unit,
    ) {
        AuthDestination(
            onLoginSuccess = onLoginSuccess,
            onExitRequest = onExitRequest
        )

    }

}