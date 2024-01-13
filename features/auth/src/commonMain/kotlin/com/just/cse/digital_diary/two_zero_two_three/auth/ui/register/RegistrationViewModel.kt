package com.just.cse.digital_diary.two_zero_two_three.auth.ui.register

import com.just.cse.digital_diary.two_zero_two_three.auth.ui.login.LoginViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class RegistrationFormData(
    val name: String="",
    val email: String = "",
    val username: String = "",
    val dept:String="",
    val password: String = "",
    val confirmPassword: String = ""
)

class RegistrationViewModel(
    val scope: CoroutineScope
) {
    companion object {
        private const val TAG = "RegistrationViewModel: "
        private fun log(message: String) {
            println("$TAG$message")
        }
    }
    private val _data= MutableStateFlow(RegistrationFormData())
    val data=_data.asStateFlow()

    fun onFullNameChanged(fullName: String) {
        _data.update { it.copy(name =fullName ) }
    }


    fun onEmailChanged(email: String) {
        _data.update { it.copy(email=email) }
    }

    fun onUsernameChanged(username: String) {
        _data.update { it.copy(username=username) }
    }

    fun onDeptChanged(dept: String) {
        _data.update { it.copy(dept=dept) }
    }


    fun onPasswordChanged(password: String) {
        _data.update { it.copy(password=password) }
    }

    fun onConfirmedPasswordChanged(confirmedPassword: String) {
        _data.update { it.copy(confirmPassword =confirmedPassword) }
    }

    private val _showProcessBar = MutableStateFlow(false)
    val showProcessBar = _showProcessBar.asStateFlow()
    private val _screenMessage = MutableStateFlow<String?>(null)
    val screenMessage = _screenMessage.asStateFlow()
    private fun updateScreenMessage(msg: String) {
        scope.launch {
            _screenMessage.value = msg
            delay(1000)
            clearScreenMessage()
        }
    }


    private fun clearScreenMessage() {
        _screenMessage.value = null
    }

    init {
        scope.launch {
            screenMessage.collect { msg ->
                if (msg != null) {
                    LoginViewModel.log(msg)

                }
            }
        }
    }


    suspend fun onRegistrationRequest(): Boolean {

        log("${data.value}")
        _showProcessBar.value = true
        delay(2000) //pretend for network IO

        val success = if (_data.value.email.contains("just.edu.bd")) {
            onRegisterSuccess()
            true
        } else {
            onRegisterFailure()
            false
        }
        _showProcessBar.value = false
        return success
    }

    private fun onRegisterSuccess() {
        updateScreenMessage("Register Success")
    }

    private fun onRegisterFailure() {
        updateScreenMessage("Register Failure,email must contains just.edu.bd")
    }

    fun onLoginRequest() {
        // println("onLoginRequest, ${userName.value}, ${password.value}")
    }
}
