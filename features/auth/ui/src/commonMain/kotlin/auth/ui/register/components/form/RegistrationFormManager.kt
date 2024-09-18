package auth.ui.register.components.form

import auth.ui.register.components.form.event.RegisterFormEvent
import auth.ui.register.components.form.state.RegistrationFormData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


internal class RegistrationFormManager {
    private val _data = MutableStateFlow(
        RegistrationFormData(
            name = "khalek", email = "khale@just.edu.bd", username = "khalek", password = "test@123", dept = "", confirmPassword = "test@123"
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
