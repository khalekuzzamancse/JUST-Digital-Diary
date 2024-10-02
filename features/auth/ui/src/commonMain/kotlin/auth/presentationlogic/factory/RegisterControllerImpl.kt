@file:Suppress("unused", "functionName")

package auth.presentationlogic.factory

import auth.domain.exception.CustomException
import auth.domain.usecase.RegisterUseCase
import auth.presentationlogic.model.RegisterModel
import auth.presentationlogic.controller.RegisterController
import auth.domain.model.AccountVerifyModel
import auth.domain.usecase.AccountVerifyUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


internal class RegisterControllerImpl(
    private val registerUseCase: RegisterUseCase,
    private val verifyUseCase: AccountVerifyUseCase,
    override val validator: RegisterController.Validator
) : RegisterController {
    private val _state = MutableStateFlow(RegisterModel("", "", "", "", ""))
    private val _isRegistering = MutableStateFlow(false)
    private val _screenMessage = MutableStateFlow<String?>(null)


    //
    override val errorMessage = _screenMessage.asStateFlow()
    override val state = _state.asStateFlow()
    override val isRegistering: StateFlow<Boolean> = _isRegistering

    override fun onNameChanged(value: String) = _state.update { it.copy(name = value.trimStart().trimEnd()) }

    override fun onEmailChanged(value: String) = _state.update { it.copy(email = value.trimStart().trimEnd()) }

    override fun onUsernameChanged(value: String) = _state.update { it.copy(username = value.trimStart().trimEnd()) }

    override fun onPasswordChanged(value: String) = _state.update { it.copy(password = value.trimStart().trimEnd()) }

    override fun onConfirmPasswordChanged(value: String) =
        _state.update { it.copy(confirmPassword = value.trimStart().trimEnd()) }


    init {
        validator.observeFieldChanges(state)
    }

    override suspend fun register() {
        _executeAndConsumeResult(
            useCaseAction = { registerUseCase.execute(_createModel()) }
        )
    }

    override suspend fun verifyAccount(username: String, otp: String) {
        _executeAndConsumeResult(
            useCaseAction = { verifyUseCase.execute(AccountVerifyModel(username.trimStart().trimEnd(), otp.trimStart().trimEnd())) }
        )
    }






    private suspend fun <T> _executeAndConsumeResult(
        useCaseAction: suspend () -> Result<T>,
        onSuccessMessage: String = "Success"
    ) {
        startLoading()
        val result = useCaseAction()
        stopLoading()

        result.fold(
            onSuccess = {
                // Handle success, with customizable success message
                _updateErrorMessage(onSuccessMessage)
            },
            onFailure = { exception ->
                when (exception) {
                    is CustomException -> {
                        _updateErrorMessage(exception.message)
                    }
                    else -> {
                        _updateErrorMessage("Something went wrong")
                    }
                }
            }
        )
    }


    private fun _createModel() = auth.domain.model.RegisterModel(
        name = state.value.name,
        username = state.value.username,
        email = state.value.email,
        password = state.value.password
    )


    //TODO:Helper methods section-----------
    private fun startLoading() = _isRegistering.update { true }
    private fun stopLoading() = _isRegistering.update { false }
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
