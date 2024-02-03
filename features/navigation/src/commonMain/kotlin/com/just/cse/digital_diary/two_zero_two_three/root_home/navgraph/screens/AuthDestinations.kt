package com.just.cse.digital_diary.two_zero_two_three.root_home.navgraph.screens

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.two_zero_two_three.auth.destination.AuthDestinationEvent

import com.just.cse.digital_diary.two_zero_two_three.root_home.destinations.destinations.AuthDestination

object AuthDestinations {
    val AUTH=Destination.createDestination("AUTH")
    @Composable
    fun Auth(
        onLoginSuccess:()->Unit,
        onExitRequest:()->Unit,
    ) {
        AuthDestination(
            onEvent ={event->
                when (event){
                    is AuthDestinationEvent.LoginSuccess->onLoginSuccess()
                    is AuthDestinationEvent.ExitRequest->onExitRequest()
                }

            }
        )

    }

}