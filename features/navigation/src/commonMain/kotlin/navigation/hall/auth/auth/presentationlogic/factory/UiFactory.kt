package navigation.hall.auth.auth.presentationlogic.factory

import navigation.hall.di.DiContainer
import navigation.hall.auth.auth.presentationlogic.controller.LoginController
import navigation.hall.auth.auth.presentationlogic.controller.PasswordResetController
import navigation.hall.auth.auth.presentationlogic.controller.RegisterController

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
    fun createForgetController(): PasswordResetController =
        PasswordRestControllerImpl(
            requestUseCase = DiContainer.createSendPasswordResetUseCase(),
            resetUseCase = DiContainer.createResetPasswordUseCase(),
            validateCase = DiContainer.codeValidateCase()
        )

}