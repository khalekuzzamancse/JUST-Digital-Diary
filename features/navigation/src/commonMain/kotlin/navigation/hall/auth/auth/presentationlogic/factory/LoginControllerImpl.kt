@file:Suppress("unused", "functionName")

package navigation.hall.auth.auth.presentationlogic.factory
import navigation.hall.domain.usecase.LoginUseCase
import navigation.hall.auth.auth.presentationlogic.model.LoginModel
import navigation.hall.auth.auth.presentationlogic.controller.LoginController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import navigation.hall.presentation.ui.CustomException


typealias LoginDomainModel = navigation.hall.domain.model.LoginModel

internal class LoginControllerImpl(
    private val useCase: LoginUseCase,
    override val validator: LoginController.Validator,

    ) : LoginController {
   private val _state = MutableStateFlow(LoginModel("admin", "admin"))

   // private val _state = MutableStateFlow(LoginModel("", ""))
    private val _isLogging = MutableStateFlow(false)
    private val _screenMessage = MutableStateFlow<String?>(null)

    //
    override val errorMessage = _screenMessage.asStateFlow()
    override val isLogging = _isLogging.asStateFlow()

    override val state = _state.asStateFlow()

    override fun onUserNameChanged(value: String) = _state.update {
        it.copy(username = value.trimStart().trimEnd())
    }


    override fun onPasswordChanged(value: String) =
        _state.update { it.copy(password = value.trimStart().trimEnd()) }


    init {
        validator.observeFieldChanges(state)
    }

    override suspend fun performLogin(): Boolean {
        return state.value.username=="admin"&&state.value.password=="admin"
//        startLoading()
//        val result = useCase.execute(
//            LoginDomainModel(username = state.value.username, state.value.password)
//        )
//        stopLoading()
//        return result.fold(
//            onSuccess = { token ->
//                _updateErrorMessage("Login Success")
//                token
//            },
//            onFailure = { exception ->
//                when (exception) {
//                    is CustomException -> {
//                        println(exception)
//                        _updateErrorMessage(exception.message)
//                    }
//
//                    else -> {
//                        _updateErrorMessage("Something went wrong")
//                    }
//
//                }
//                null
//            }
//        )

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

    private fun _log(msg: String) {
        println("${this.javaClass.simpleName}:$msg")
    }
}