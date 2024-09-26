@file:Suppress("unused", "functionName")

package auth.factory

import auth.domain.exception.CustomException
import auth.domain.usecase.RegisterUseCase
import auth.model.RegisterModel
import auth.ui.register.RegisterController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


internal class RegisterControllerImpl(
    private val useCase: RegisterUseCase,
    override val validator: RegisterController.Validator
) : RegisterController {
    private val _state = MutableStateFlow(RegisterModel("", "", "", "", ""))
    private val _isRegistering = MutableStateFlow(false)
    private val _screenMessage = MutableStateFlow<String?>(null)


    //
    override val errorMessage = _screenMessage.asStateFlow()
    override val state = _state.asStateFlow()
    override val isRegistering: StateFlow<Boolean> = _isRegistering

    override fun onNameChanged(value: String) = _state.update { it.copy(name = value) }

    override fun onEmailChanged(value: String) = _state.update { it.copy(email = value) }

    override fun onUsernameChanged(value: String) = _state.update { it.copy(username = value) }

    override fun onPasswordChanged(value: String) = _state.update { it.copy(password = value) }

    override fun onConfirmPasswordChanged(value: String) =
        _state.update { it.copy(confirmPassword = value) }


    init {
        validator.observeFieldChanges(state)
    }

    override suspend fun register() {
        startLoading()
        val result = useCase.execute(_createModel())
        stopLoading()

        return result.fold(
            onSuccess = {
                //It it hard to denote that success and what message should be shown??
                //that is why it is user responsibility to navigate back if need
                _updateErrorMessage("Success")
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
