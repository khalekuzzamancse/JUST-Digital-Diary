package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.register.destination.viewmodel

import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.register.components.controls.RegisterControlEvents
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.register.components.form.RegistrationFormManager
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.register.destination.events.RegisterDestinationEvent
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.register.destination.states.RegisterDestinationState
import com.just.cse.digital_diary.two_zero_two_three.domain.register.model.RegisterRequestModel
import com.just.cse.digital_diary.two_zero_two_three.domain.register.model.RegisterResponseModel
import com.just.cse.digital_diary.two_zero_two_three.domain.register.repository.RegisterRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterDestinationViewModel(
    private val repository: RegisterRepository
) {
    private val _state = MutableStateFlow(RegisterDestinationState())
    val state = _state.asStateFlow()
    private val formManager = RegistrationFormManager()
    internal val formEvent = formManager.event
    internal val formData = formManager.data
    fun onEvent(event: RegisterDestinationEvent) {
        when (event) {
            is RegisterControlEvents -> onControlsEvent(event)
        }
    }

    var onPublicEvent: (RegisterDestinationEvent) -> Unit = {

    }

    private fun onControlsEvent(event: RegisterControlEvents) {
        when (event) {
            RegisterControlEvents.LoginRequest -> {
                onPublicEvent(RegisterDestinationEvent.ExitRequest)
            }

            RegisterControlEvents.RegisterRequest -> onRegisterRequest()
        }

    }

    private fun onRegisterRequest() {
        CoroutineScope(Dispatchers.IO).launch {
            startLoading()
            val response = repository.register(getRegistrationModel())
            if (response is RegisterResponseModel.Success) {
                onRegisterSuccess()
            } else if (response is RegisterResponseModel.Failure) {
                onRegisterFailure(response.reason)
            }
        }

    }

    private fun getRegistrationModel(): RegisterRequestModel {
        val username = formData.value.username
        val password = formData.value.password
        val email = formData.value.email
        val name = formData.value.name
        return RegisterRequestModel(
            username = username,
            password = password,
            name = name,
            email = email
        )
    }

    private suspend fun onRegisterFailure(reason: String?) {
        stopLoading()
        updateSnackBarMessage("Registration failed because of $reason")
        delay(1500)
        clearMessages()
    }

    private suspend fun onRegisterSuccess() {
        stopLoading()
        updateSnackBarMessage("Register Successful")
        delay(1500)
        clearMessages()
        onPublicEvent(RegisterDestinationEvent.ExitRequest)

    }

    private fun clearMessages() {
        updateSnackBarMessage(null)
    }

    private fun updateSnackBarMessage(message: String?) {
        _state.update { it.copy(message = message) }
    }

    private fun startLoading() {
        _state.update { it.copy(isLoading = true) }
    }

    private fun stopLoading() {
        _state.update { it.copy(isLoading = false) }
    }


}