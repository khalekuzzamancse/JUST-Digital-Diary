package com.just.cse.digital_diary.two_zero_two_three.ui_layer.login.login_destination.viewmodel

import com.just.cse.digital_diary.two_zero_two_three.domain_layer.login.repoisitory.LoginRepository
import com.just.cse.digital_diary.two_zero_two_three.ui_layer.login.components.controls.LoginControlsLoginModuleEvent
import com.just.cse.digital_diary.two_zero_two_three.ui_layer.login.components.form.LoginFormEvent
import com.just.cse.digital_diary.two_zero_two_three.ui_layer.login.components.form.LoginFormManager
import com.just.cse.digital_diary.two_zero_two_three.ui_layer.login.login_destination.event.DestinationLoginModuleEvent
import com.just.cse.digital_diary.two_zero_two_three.ui_layer.login.login_destination.event.LoginModuleEvent
import com.just.cse.digital_diary.two_zero_two_three.ui_layer.login.login_destination.states.DestinationState
import com.just.cse.digital_diary.two_zero_two_three.ui_layer.login.login_destination.states.FormData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginDestinationViewModel(
    private val repository: LoginRepository
) {
    private val _state = MutableStateFlow(DestinationState.toEmpty())
    val state = _state.asStateFlow()
    private val formManager= LoginFormManager()
    val formEvent =formManager.event
    val formData = formManager.data

    fun onEvent(loginModuleEvent: LoginModuleEvent) {
        when (loginModuleEvent) {
            is LoginControlsLoginModuleEvent -> onControlsEvent(loginModuleEvent)
            DestinationLoginModuleEvent.ExitRequest -> onExitRequest()
        }
    }


    private fun onControlsEvent(event: LoginControlsLoginModuleEvent) {
        when (event) {
            LoginControlsLoginModuleEvent.LoginModuleRequest -> onLoginRequest()
            LoginControlsLoginModuleEvent.RegisterRequest -> TODO("onRegisterRequest()")
            LoginControlsLoginModuleEvent.PasswordResetRequest -> TODO("onPasswordResetRequest()")
        }

    }

    private fun onLoginRequest() {

    }


    private fun onExitRequest() {

    }

}