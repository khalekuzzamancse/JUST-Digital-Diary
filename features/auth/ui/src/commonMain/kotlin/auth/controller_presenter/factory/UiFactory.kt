package auth.controller_presenter.factory

import auth.di.DiContainer
import auth.controller_presenter.controller.LoginController
import auth.controller_presenter.controller.RegisterController

internal object UiFactory {

    fun createLoginController(): LoginController =
        LoginControllerImpl(
            useCase = DiContainer.createLoginUseCase(),
            validator = LoginValidatorImpl()
        )

    fun createRegisterController(): RegisterController =
        RegisterControllerImpl(
            useCase = DiContainer.createRegisterUseCase(),
            validator = RegisterValidatorImpl()
        )
}