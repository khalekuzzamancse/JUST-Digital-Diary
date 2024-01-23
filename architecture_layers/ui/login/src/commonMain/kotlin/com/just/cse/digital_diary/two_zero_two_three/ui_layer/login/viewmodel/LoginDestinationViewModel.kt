package com.just.cse.digital_diary.two_zero_two_three.ui_layer.login.viewmodel

import com.just.cse.digital_diary.two_zero_two_three.data_layer.login.repoisitory.LoginRepository
import com.just.cse.digital_diary.two_zero_two_three.ui_layer.login.event.DestinationEvent
import com.just.cse.digital_diary.two_zero_two_three.ui_layer.login.states.DestinationState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginDestinationViewModel(
    private val repository: LoginRepository
) {
    private val _state = MutableStateFlow(DestinationState.toEmpty())
    val state = _state.asStateFlow()
    fun onEvent(event: DestinationEvent) {
        when (event) {
            DestinationEvent.LoginRequest -> onLoginRequest()
            DestinationEvent.ExitRequest -> onExitRequest()
        }
    }

    private fun onLoginRequest() {

    }

    private fun onExitRequest() {

    }

}