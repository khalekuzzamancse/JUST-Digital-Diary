package auth.controller_presenter.factory

import auth.controller_presenter.model.LoginModel
import auth.controller_presenter.controller.LoginController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

internal class LoginValidatorImpl : LoginController.Validator {
    private val _areAllFieldsFilled = MutableStateFlow(false)
    private val _errors = MutableStateFlow<List<String>>(emptyList())

    override val areAllFieldsFilled: StateFlow<Boolean> = _areAllFieldsFilled.asStateFlow()

    override val errors: StateFlow<List<String>> = _errors.asStateFlow()

    override fun observeFieldChanges(input: StateFlow<LoginModel>) {
        input
            .onEach { model ->
                val (usernameOrEmail, password) = model

                _areAllFieldsFilled.value =
                    usernameOrEmail.length >=3 && password.length >= 6
            }
            .launchIn(CoroutineScope(Dispatchers.Default)) // or use the correct scope for your flow

    }

}