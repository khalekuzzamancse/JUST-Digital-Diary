package auth.factory

import auth.ui.login.LoginController
import auth.model.LoginModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

internal class LoginControllerImpl : LoginController {
    private val _state = MutableStateFlow(LoginModel("", ""))
    override val isLogging: StateFlow<Boolean>
        get() = TODO("Not yet implemented")
    override val state = _state.asStateFlow()

    override fun onUserNameChanged(value: String) = _state.update { it.copy(username = value) }

    override fun onPasswordChanged(value: String) = _state.update { it.copy(password = value) }

    override fun performLogin(): Boolean {
        return false
    }
}