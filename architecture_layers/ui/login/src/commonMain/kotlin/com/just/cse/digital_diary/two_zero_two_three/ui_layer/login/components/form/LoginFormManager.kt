package com.just.cse.digital_diary.two_zero_two_three.ui_layer.login.components.form

import com.just.cse.digital_diary.two_zero_two_three.ui_layer.login.login_destination.states.FormData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginFormManager {
    val event = LoginFormEvent(
        onUserNameChanged = ::onUserNameChanged,
        onPasswordChanged = ::onPasswordChanged
    )
    private val _data = MutableStateFlow(
        FormData(
            username = "khalek02", password = "test@123"
        )
    )
    val data = _data.asStateFlow()
    private fun onUserNameChanged(username: String) {
        _data.update { it.copy(username = username) }
    }

    private fun onPasswordChanged(password: String) {
        _data.update { it.copy(password = password) }
    }


}

