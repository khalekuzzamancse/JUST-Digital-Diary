package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.register.destination.viewmodel
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.register.components.form.RegistrationFormManager
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.register.destination.states.RegisterDestinationState
import com.just.cse.digital_diary.two_zero_two_three.domain.register.model.RegisterRequestModel
import com.just.cse.digital_diary.two_zero_two_three.domain.register.model.RegisterResponseModel
import com.just.cse.digital_diary.two_zero_two_three.domain.register.repository.RegisterRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RegisterDestinationViewModel(
    private val repository: RegisterRepository
) {
    private val _state = MutableStateFlow(RegisterDestinationState())
    val state = _state.asStateFlow()
    private val formManager = RegistrationFormManager()
    internal val formEvent = formManager.event
    internal val formData = formManager.data


    fun clearState() {
        _state.update { RegisterDestinationState() }
    }



     suspend fun register():Boolean {
            startLoading()
            val response = repository.register(getRegistrationModel())
            if (response is RegisterResponseModel.Success) {
                onRegisterSuccess()
                return true
            } else if (response is RegisterResponseModel.Failure) {
                onRegisterFailure(response.reason)
                return false
            }
         return false
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