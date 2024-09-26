package auth.ui.register

import auth.model.RegisterModel
import kotlinx.coroutines.flow.StateFlow

/**
 * - Keeps an abstract `validator`, forcing the implementer to provide a separate implementation
 *   for validation. This ensures single responsibility and separation of concerns, so if any
 *   validation logic needs to change or need separate implementation for validate, there is no need to modify the `Controller`
 * - By defining the `validator` here, it also controls which `fields` should be validated
 */
internal interface RegisterController {
    val isRegistering: StateFlow<Boolean>
    val errorMessage: StateFlow<String?>
    val state: StateFlow<RegisterModel>
    val validator:Validator
    fun onNameChanged(value: String)
    fun onEmailChanged(value: String)
    fun onUsernameChanged(value: String)
    fun onPasswordChanged(value: String)
    fun onConfirmPasswordChanged(value: String)
    suspend fun register()

    interface Validator {
        val areAllFieldsFilled: StateFlow<Boolean>
        val errors: StateFlow<List<String>>
        fun observeFieldChanges(inputData: StateFlow<RegisterModel>)

    }
}