package auth.factory

import auth.model.RegisterModel
import auth.ui.register.RegisterController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


internal class RegisterControllerImpl : RegisterController {
    private val _state = MutableStateFlow(RegisterModel("", "", "", "", ""))
    private val _isRegistering = MutableStateFlow(false)

    //
    override val state = _state.asStateFlow()
    override val isRegistering: StateFlow<Boolean> = _isRegistering

    override fun onNameChanged(value: String) = _state.update { it.copy(name = value) }

    override fun onEmailChanged(value: String) = _state.update { it.copy(email = value) }

    override fun onUsernameChanged(value: String) = _state.update { it.copy(username = value) }

    override fun onPasswordChanged(value: String) = _state.update { it.copy(password = value) }

    override fun onConfirmPasswordChanged(value: String) =
        _state.update { it.copy(confirmPassword = value) }


    override fun register() {

    }
}
