package auth.presentationlogic.factory

import auth.domain.exception.CustomException
import auth.domain.model.ResetPasswordModel
import auth.domain.usecase.ResetPasswordUseCase
import auth.domain.usecase.SendPasswordResetUseCase
import auth.presentationlogic.controller.PasswordResetController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class PasswordRestControllerImpl(
    private val requestUseCase: SendPasswordResetUseCase,
    private val resetUseCase: ResetPasswordUseCase
) : PasswordResetController {
    //    private val _state = MutableStateFlow(LoginModel("190142.cse@student.just.edu.bd", "12345678"))

    private val _isRequestSending = MutableStateFlow(false)
    private val _screenMessage = MutableStateFlow<String?>(null)
    private val _email = MutableStateFlow("")
    private val _password = MutableStateFlow("")
    private val _code = MutableStateFlow("")
    private val _isSendResetRequestSuccessful = MutableStateFlow(false)

    //
    override val errorMessage = _screenMessage.asStateFlow()
    override val isRequestSending = _isRequestSending.asStateFlow()
    override val email = _email.asStateFlow()
    override val newPassword = _password.asStateFlow()
    override val code = _code.asStateFlow()
    override val isSendResetRequestSuccessful = _isSendResetRequestSuccessful.asStateFlow()

    override fun onEmailChanged(value: String) {
        _email.update { value }
    }

    override fun onPasswordChanged(value: String) {
        _password.update { value }
    }

    override fun onCodeChanged(value: String) {
        _code.update { value }
    }


    override suspend fun sendResetRequest() {
        _isSendResetRequestSuccessful.update { true } //TODO: for debugging purposes
        startLoading()
        requestUseCase.execute(email.value).fold(
            onSuccess = {
                _updateErrorMessage("Reset request sent")
                _isSendResetRequestSuccessful.update { true }
            },
            onFailure = { exception ->
                if (exception is CustomException) {
                    _updateErrorMessage(exception.message)
                } else {
                    _updateErrorMessage("Something went wrong")
                }

            }
        )
        stopLoading()

    }

    override suspend fun resetPassword() {
        handleApiCall(
            execute = {
                resetUseCase.execute(
                    ResetPasswordModel(
                        email = email.value,
                        code = code.value,
                        newPassword = newPassword.value
                    )
                )
            },
            onSuccessMessage = "Reset successfully"
        )
    }


    //TODO:Helper methods section-----------
    // Helper function for handling API requests with loading and error handling
    private suspend fun <T> handleApiCall(
        execute: suspend () -> Result<T>,
        onSuccessMessage: String,
        onFailureMessage: String = "Something went wrong"
    ) {
        startLoading()
        val result = execute()
        stopLoading()

        result.fold(
            onSuccess = {
                _updateErrorMessage(onSuccessMessage)
            },
            onFailure = { exception ->
                val errorMessage = if (exception is CustomException) {
                    exception.message
                } else {
                    onFailureMessage
                }
                _updateErrorMessage(errorMessage)
            }
        )
    }

    private fun startLoading() = _isRequestSending.update { true }
    private fun stopLoading() = _isRequestSending.update { false }
    private fun _updateErrorMessage(msg: String) {
        CoroutineScope(Dispatchers.Default).launch {
            _screenMessage.update { msg }
            //clear after 4 seconds
            delay(4_000)
            _screenMessage.update { null }
        }
    }

    private fun _log(msg: String) {
        println("${this.javaClass.simpleName}:$msg")
    }
}