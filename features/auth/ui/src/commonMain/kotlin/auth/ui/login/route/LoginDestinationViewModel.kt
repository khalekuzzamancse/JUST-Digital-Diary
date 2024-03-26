package auth.ui.login.route

import auth.domain.login.model.LoginRequestModel
import auth.domain.login.model.LoginResponseModel
import auth.domain.login.repoisitory.LoginRepository
import auth.ui.login.components.event.LoginEvent
import auth.ui.login.components.form.LoginFormManager
import common.newui.CustomSnackBarData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class LoginDestinationViewModel(
    private val repository: LoginRepository,
) {

    companion object {
        private const val TAG = "LoginDestinationViewModel : "
        private fun log(message: String) {
            println("$TAG$message")
        }
    }

    private val _state = MutableStateFlow(LoginDestinationState())
    val state = _state.asStateFlow()
    private val formManager = LoginFormManager()
    val formEvent = formManager.event
    val formData = formManager.data


    suspend fun login(): LoginEvent.LoginDestinationEvent.LoginSuccess? {
        startLoading()
        val username = formData.value.username
        val password = formData.value.password
        val response: Result<LoginResponseModel> =
            repository.login(LoginRequestModel(username = username, password = password))

        return if (response.isSuccess) {
            onLoginSuccess()
            response.getOrNull()
            LoginEvent.LoginDestinationEvent.LoginSuccess(
                username = username, password = password
            )
        } else {
            onLoginFailure(response.exceptionOrNull())
            null
        }

    }

    private suspend fun onLoginFailure(ex: Throwable?) {
        stopLoading()
        val reason =
            if (ex == null) "Login Failed with No Reason mentioned:LoginDestinationViewModel:onLoginFailure()"
            else ex.message
        updateSnackBarMessage(message = "Login Failed", details = reason, isError = true)
        //do not clear the message,user will dismiss the dialogue
//        clearMessages()
    }

    private suspend fun onLoginSuccess() {
        stopLoading()
        updateSnackBarMessage("Login Successful")
        delay(1500)
        clearMessages()
    }

    private fun clearMessages() {
        updateSnackBarMessage(null)
    }

    private fun updateSnackBarMessage(
        message: String?,
        details: String? = null,
        isError: Boolean = false
    ) {
        if (message != null) {
            _state.update {
                it.copy(
                    snackBarData = CustomSnackBarData(message, details, isError)
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