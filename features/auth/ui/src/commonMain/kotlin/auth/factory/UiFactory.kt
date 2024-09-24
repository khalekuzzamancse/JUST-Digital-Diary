package auth.factory

import auth.ui.login.LoginController
import auth.ui.register.RegisterController

internal object UiFactory {

    fun createLoginController(): LoginController =
        LoginControllerImpl()
    fun createRegisterController(): RegisterController =
        RegisterControllerImpl()
}