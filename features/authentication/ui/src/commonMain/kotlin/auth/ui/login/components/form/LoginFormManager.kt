package auth.ui.login.components.form

import auth.ui.login.components.form.state.FormData
import auth.ui.login.components.form.state.LoginFormEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

internal class LoginFormManager {
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

