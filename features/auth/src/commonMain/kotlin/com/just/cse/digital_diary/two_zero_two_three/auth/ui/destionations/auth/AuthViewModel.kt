package com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.auth

import com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.login.form.LoginFormManager
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.registration.form.RegistrationFormManager
import com.just.cse.digitaldiary.twozerotwothree.data.repository.auth_repository.data.AuthenticationManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel(
    val onLoginSuccess: () -> Unit,
) {
    private val scope = CoroutineScope(Dispatchers.Default)
    val event = AuthScreenEvent(
        onRegistrationRequest = ::onRegisterRequest,
        onRegistrationFromOpenRequest = ::openRegisterForm,
        onRegistrationFromCloseRequest = ::closeRegisterDestination,
        onLoginRequest = ::onLoginRequest
    )

    val loginFormManager = LoginFormManager()

    var registrationFormManager: RegistrationFormManager? = null
        private set
    private val _registerDestinationOpened = MutableStateFlow(false)
    val registrationDestinationOpened = _registerDestinationOpened.asStateFlow()


    private val _showProcessBar = MutableStateFlow(false)
    private val _screenMessage = MutableStateFlow<String?>(null)
    val showProcessBar = _showProcessBar.asStateFlow()
    val screenMessage = _screenMessage.asStateFlow()

    private fun openRegisterForm() {
        _registerDestinationOpened.update { true }
        registrationFormManager = RegistrationFormManager()

    }

    private fun closeRegisterDestination() {
        _registerDestinationOpened.update { false }
        registrationFormManager = null
    }

    private fun onLoginRequest() {
        scope.launch {
            _showProcessBar.update { true }
            val isSuccess = AuthenticationManager.login(
                username = loginFormManager.data.value.username,
                password = loginFormManager.data.value.password
            )
            if (isSuccess) {
                delay(1500)
                _screenMessage.update { "Login Success" }
                delay(1500)
                _screenMessage.update { null }
                _showProcessBar.update { false }
                onLoginSuccess()
            } else {
                _screenMessage.update { "Login Failed,User name or password wrong" }
                delay(2000)
                _screenMessage.update { null }
                _showProcessBar.update { false }
            }

        }


    }

    private fun onRegisterRequest() {
        scope.launch {
            _showProcessBar.update { true }
            registrationFormManager?.let { registrationFormManager ->
                println("RegisterDta: ${registrationFormManager.data.value}")
                val isSuccess = AuthenticationManager.register(
                    name = registrationFormManager.data.value.name,
                    email = registrationFormManager.data.value.email,
                    username = registrationFormManager.data.value.username,
                    password = registrationFormManager.data.value.password
                )

                if (isSuccess) {
                    delay(1500)
                    _screenMessage.update { "Register  Success" }
                    delay(1500)
                    _screenMessage.update { null }
                    _showProcessBar.update { false }
                    closeRegisterDestination()
                } else {
                    _screenMessage.update { "Register Failed" }
                    delay(2000)
                    _screenMessage.update { null }
                    _showProcessBar.update { false }
                }
            }



        }


    }


}