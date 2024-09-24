package auth.ui.register

import auth.model.RegisterModel
import kotlinx.coroutines.flow.StateFlow

internal interface RegisterController {
    val isRegistering: StateFlow<Boolean>
    val state: StateFlow<RegisterModel>
    fun onNameChanged(value: String)
    fun onEmailChanged(value: String)
    fun onUsernameChanged(value: String)
    fun onPasswordChanged(value: String)
    fun onConfirmPasswordChanged(value: String)
    fun register()
}