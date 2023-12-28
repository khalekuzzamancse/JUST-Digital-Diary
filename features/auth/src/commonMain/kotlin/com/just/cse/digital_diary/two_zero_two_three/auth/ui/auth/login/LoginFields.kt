package com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.login

import com.just.cse.digital_diary.features.common_ui.form.LabelLessTextFieldState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


/*
State of the screen
userName,Password,trailing icon
 */

class LoginFieldsState {
    private val _showPassword = MutableStateFlow(false)
    val showPassword = _showPassword.asStateFlow()

    private val _userNameState = MutableStateFlow(LabelLessTextFieldState())
    val userName = _userNameState.asStateFlow()
    fun onUserNameChanged(userName: String) {
        _userNameState.update { it.copy(value = userName) }
    }

    private val _passwordState = MutableStateFlow(LabelLessTextFieldState())
    val password = _passwordState.asStateFlow()

    fun onPasswordChanged(password: String) {
        _passwordState.update { it.copy(value = password) }
    }

    fun onTogglePasswordVisibility() {
        _showPassword.update { !it }
    }

}


