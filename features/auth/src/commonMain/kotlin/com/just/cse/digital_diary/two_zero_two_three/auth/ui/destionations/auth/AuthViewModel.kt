package com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.auth

import com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.login.form.LoginFormManager
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.registration.form.RegistrationFormManager
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
    private val scope= CoroutineScope(Dispatchers.Default)
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
            delay(1500)
            _screenMessage.update { "Login Success" }
            delay(1500)
            _screenMessage.update { null }
            _showProcessBar.update { false }
            onLoginSuccess()
        }


    }

    private fun onRegisterRequest() {
        scope.launch {
            _showProcessBar.update { true }
            delay(1500)
            _screenMessage.update { "Register Success" }
            delay(1500)
            _screenMessage.update { null }
            _showProcessBar.update { false }
            closeRegisterDestination()
        }

    }


}