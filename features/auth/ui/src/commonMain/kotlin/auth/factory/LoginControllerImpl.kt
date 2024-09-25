@file:Suppress("unused", "functionName")

package auth.factory

import auth.domain.exception.CustomException
import auth.domain.usecase.LoginUseCase
import auth.model.LoginModel
import auth.ui.login.LoginController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

typealias LoginDomainModel = auth.domain.model.LoginModel

internal class LoginControllerImpl(
    private val useCase: LoginUseCase
) : LoginController {
    private val _state = MutableStateFlow(LoginModel("", ""))
    private val _isLogging = MutableStateFlow(false)
    private val _screenMessage = MutableStateFlow<String?>(null)

    //
    override val errorMessage = _screenMessage.asStateFlow()
    override val isLogging = _isLogging.asStateFlow()

    override val state = _state.asStateFlow()

    override fun onUserNameChanged(value: String) = _state.update { it.copy(username = value) }

    override fun onPasswordChanged(value: String) = _state.update { it.copy(password = value) }

    override suspend fun performLogin(): Boolean {
        startLoading()
        val result = useCase.execute(
            LoginDomainModel(username = state.value.username, state.value.password)
        )
        stopLoading()

        return result.fold(
            onSuccess = {
                _updateErrorMessage("Login Success")
                true
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
                false
            }
        )

    }


    //TODO:Helper methods section-----------
    private fun startLoading() = _isLogging.update { true }
    private fun stopLoading() = _isLogging.update { false }
    private fun _updateErrorMessage(msg: String) {
        CoroutineScope(Dispatchers.Default).launch {
            _screenMessage.update { msg }
            //clear after 4 seconds
            delay(4_000)
            _screenMessage.update { null }
        }
    }
}