package com.just.cse.digital_diary.two_zero_two_three.ui_layer.login.login_destination.viewmodel

import com.just.cse.digital_diary.two_zero_two_three.domain_layer.login.model.LoginRequestModel
import com.just.cse.digital_diary.two_zero_two_three.domain_layer.login.model.LoginResponseModel
import com.just.cse.digital_diary.two_zero_two_three.domain_layer.login.repoisitory.LoginRepository
import com.just.cse.digital_diary.two_zero_two_three.ui_layer.login.components.form.LoginFormManager
import com.just.cse.digital_diary.two_zero_two_three.ui_layer.login.login_destination.states.LoginDestinationState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginDestinationViewModel(
    private val repository: LoginRepository,
) {

    companion object {
        private const val TAG = "LoginDestinationViewModel : "
        private fun log(message: String) {
            println("$TAG$message")
        }
    }

    private val _state = MutableStateFlow(LoginDestinationState())
    val state = _state.asStateFlow()
    private val formManager = LoginFormManager()
    val formEvent = formManager.event
    val formData = formManager.data


    suspend fun login(): Boolean {
        startLoading()
        val username = formData.value.username
        val password = formData.value.password
        val response = repository.login(
            LoginRequestModel(username = username, password = password)
        )
        if (response is LoginResponseModel.Success) {
            onLoginSuccess()
            return true

        } else if (response is LoginResponseModel.Failure) {
            onLoginFailure(response.reason)
            return false
        }
        return false

    }

    private suspend fun onLoginFailure(reason: String?) {
        stopLoading()
        updateSnackBarMessage("Login failed because of $reason")
        delay(1500)
        clearMessages()
    }

    private suspend fun onLoginSuccess() {
        stopLoading()
        updateSnackBarMessage("Login Successful")
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