package auth.presentationlogic.factory

import auth.presentationlogic.model.RegisterModel
import auth.presentationlogic.controller.RegisterController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

internal class RegisterValidatorImpl : RegisterController.Validator {
    private val _areAllFieldsFilled = MutableStateFlow(false)
    private val _errors = MutableStateFlow<List<String>>(emptyList())




    override val areAllFieldsFilled: StateFlow<Boolean> = _areAllFieldsFilled.asStateFlow()

    override val errors: StateFlow<List<String>> = _errors.asStateFlow()

    override fun observeFieldChanges(input: StateFlow<RegisterModel>) {
        input
            .onEach { model ->
                val errorList = mutableListOf<String>()

                val (name, email, username, password, confirmPassword) = model

                //@gmail.com has length=10 so email must have length>10
                if (email.length > 10 && !email.contains("@"))
                    errorList.add("Invalid email")

                if (confirmPassword.isNotBlank() && confirmPassword != password)
                    errorList.add("Passwords did not match")

                _errors.value = errorList

                val allFieldsFilled = name.length > 2
                        && email.length > 10
                        && username.length > 2
                        && model.password.isNotBlank()
                        && password == confirmPassword

                _areAllFieldsFilled.value = allFieldsFilled
            }
            .launchIn(CoroutineScope(Dispatchers.Default)) // or use the correct scope for your flow

    }

}