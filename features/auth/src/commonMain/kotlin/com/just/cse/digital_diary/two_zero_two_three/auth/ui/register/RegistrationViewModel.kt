package com.just.cse.digital_diary.two_zero_two_three.auth.ui.register

import com.just.cse.digital_diary.features.common_ui.form.FormTextFieldState
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.login.LoginViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegistrationViewModel(
    val scope: CoroutineScope
) {
    companion object {
        private const val TAG = "RegistrationViewModel: "
        private fun log(message: String) {
            println("$TAG$message")
        }
    }

    //FULL NAME
    private val _fullName = MutableStateFlow(FormTextFieldState(value = ""))
    val fullName = _fullName.asStateFlow()
    fun onFullNameChanged(fullName: String) {
        _fullName.update { it.copy(value = fullName) }
    }

    //Email
    private val _email = MutableStateFlow(FormTextFieldState(value = ""))
    val email = _email.asStateFlow()
    fun onEmailChanged(email: String) {
        _email.update { it.copy(value = email) }
    }

    //UserName
    private val _userName = MutableStateFlow(FormTextFieldState(value = ""))
    val userName = _userName.asStateFlow()
    fun onUsernameChanged(username: String) {
        _userName.update { it.copy(value = username) }
    }

    //
    private val _dept = MutableStateFlow(FormTextFieldState(value = ""))
    val dept = _dept.asStateFlow()
    fun onDeptChanged(dept: String) {
        _dept.update { it.copy(value = dept) }
    }

    //Password
    private val _password = MutableStateFlow(FormTextFieldState())
    val password = _password.asStateFlow()
    fun onPasswordChanged(password: String) {
        _password.update { it.copy(value = password) }
    }

    //ConfirmPassword
    private val _confirmedPassword = MutableStateFlow(FormTextFieldState())
    val confirmedPassword = _confirmedPassword.asStateFlow()
    fun onConfirmedPasswordChanged(confirmedPassword: String) {
        _confirmedPassword.update { it.copy(value = confirmedPassword) }
    }

    /*

     */
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
        val name = fullName.value.value
        val email = email.value.value
        val username = userName.value.value
        val password = password.value.value
        val confirmedPassword = confirmedPassword.value.value
        log("name: $name, email: $email, username: $username, password:$password,confirmedPassword: $confirmedPassword")
        _showProcessBar.value = true
        delay(2000) //pretend for network IO

        val success = if (email.contains("just.edu.bd")) {
            onRegisterSuccess()
            true
        } else {
            onRegisterFailure()
            false
        }
        _showProcessBar.value = false
        return success
    }

    fun onRegisterSuccess() {
        updateScreenMessage("Register Success")
    }

    private fun onRegisterFailure() {
        updateScreenMessage("Register Failure,email must contains just.edu.bd")
    }

    fun onLoginRequest() {
        // println("onLoginRequest, ${userName.value}, ${password.value}")
    }
}
