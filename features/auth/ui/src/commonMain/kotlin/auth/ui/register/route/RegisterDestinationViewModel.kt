package auth.ui.register.route

import auth.domain.register.model.RegisterRequestModel
import auth.domain.register.repository.RegisterRepository
import auth.ui.register.components.form.RegistrationFormManager
import common.newui.CustomSnackBarData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RegisterDestinationViewModel(
    private val repository: RegisterRepository
) {
    private val _state = MutableStateFlow(RegisterDestinationState())
    val state = _state.asStateFlow()
    private val formManager = RegistrationFormManager()
    internal val formEvent = formManager.event
    internal val formData = formManager.data


    fun clearState() {
        _state.update { RegisterDestinationState() }
    }


    suspend fun register(): Boolean {
        startLoading()
        val response: Result<String> = repository.register(getRegistrationModel())
        return if (response.isSuccess) {
            onRegisterSuccess()
            true
        } else {
            val ex = response.exceptionOrNull()
                ?: Throwable("Register Failed,no reason mentioned at ,RegisterDestinationViewModel:register() ")
            onRegisterFailure(ex.message)
            false
        }

    }

    private fun getRegistrationModel(): RegisterRequestModel {
        val username = formData.value.username
        val password = formData.value.password
        val email = formData.value.email
        val name = formData.value.name
        return RegisterRequestModel(
            username = username,
            password = password,
            name = name,
            email = email
        )
    }

    private suspend fun onRegisterFailure(reason: String?) {
        stopLoading()
        updateSnackBarMessage(message = "Register Failed",details =reason,isError = true)
        //let the user to dismiss the snack-bar after show details
    }

    private suspend fun onRegisterSuccess() {
        stopLoading()
        updateSnackBarMessage(message = "Register Successful")
        delay(1500)
        clearMessages()

    }

    private fun clearMessages() {
        updateSnackBarMessage(null)
    }

    private fun updateSnackBarMessage(
        message: String?,
        details: String?=null,
        isError: Boolean = false
    ) {
        if (message != null) {
            _state.update {
                it.copy(
                    snackBarData = CustomSnackBarData(
                        message = message,
                        details = details,
                        isError = isError
                    )
                )
            }
        } else {
            _state.update {
                it.copy(
                    snackBarData = null
                )
            }
        }
    }

    private fun startLoading() {
        _state.update { it.copy(isLoading = true) }
    }

    private fun stopLoading() {
        _state.update { it.copy(isLoading = false) }
    }


}