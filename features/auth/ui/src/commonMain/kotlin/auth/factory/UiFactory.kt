package auth.factory

import auth.di.DiContainer
import auth.ui.login.LoginController
import auth.ui.register.RegisterController

internal object UiFactory {

    fun createLoginController(): LoginController =
        LoginControllerImpl(
            useCase = DiContainer.createLoginUseCase()
        )
    fun createRegisterController(): RegisterController =
        RegisterControllerImpl()
}