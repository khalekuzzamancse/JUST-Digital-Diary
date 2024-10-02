package auth.presentationlogic.factory

import auth.di.DiContainer
import auth.presentationlogic.controller.LoginController
import auth.presentationlogic.controller.PasswordResetController
import auth.presentationlogic.controller.RegisterController

internal object UiFactory {

    fun createLoginController(): LoginController =
        LoginControllerImpl(
            useCase = DiContainer.createLoginUseCase(),
            validator = LoginValidatorImpl()
        )

    fun createRegisterController(): RegisterController =
        RegisterControllerImpl(
            registerUseCase = DiContainer.createRegisterUseCase(),
            verifyUseCase = DiContainer.createAccountVerifyUseCase(),
            validator = RegisterValidatorImpl()
        )
    fun createForgetController(): PasswordResetController=
        PasswordRestControllerImpl(
            requestUseCase = DiContainer.createSendPasswordResetUseCase(),
            resetUseCase = DiContainer.createResetPasswordUseCase()
        )

}