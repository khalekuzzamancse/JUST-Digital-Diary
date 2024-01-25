package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.register.components.form

import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.register.components.form.event.RegisterFormEvent
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.register.components.form.state.RegistrationFormData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class RegistrationFormManager {
    private val _data = MutableStateFlow(
        RegistrationFormData(
            name = "", email = "", username = "", password = "", dept = "", confirmPassword = ""
        )
    )
    val data = _data.asStateFlow()
    val event = RegisterFormEvent(
        onNameChanged = ::onNameChanged,
        onEmailChanged = ::onEmailChanged,
        onUserNameChanged = ::onUsernameChanged,
        onDeptChanged = ::onDeptChanged,
        onPasswordChanged = ::onPasswordChanged,
        onConfirmedPassword = ::onConfirmedPasswordChanged)

    private fun onNameChanged(fullName: String) {
        _data.update { it.copy(name = fullName) }
    }


    private fun onEmailChanged(email: String) {
        _data.update { it.copy(email = email) }
    }

    private fun onUsernameChanged(username: String) {
        _data.update { it.copy(username = username) }
    }

    private fun onDeptChanged(dept: String) {
        _data.update { it.copy(dept = dept) }
    }


    private fun onPasswordChanged(password: String) {
        _data.update { it.copy(password = password) }
    }

    private fun onConfirmedPasswordChanged(confirmedPassword: String) {
        _data.update { it.copy(confirmPassword = confirmedPassword) }
    }


}
