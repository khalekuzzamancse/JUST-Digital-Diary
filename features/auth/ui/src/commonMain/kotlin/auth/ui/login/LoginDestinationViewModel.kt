package auth.ui.login

import auth.controller_presenter.controller.LoginController
import auth.controller_presenter.controller.RegisterController
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

/**
 * - View model is UI related to is it okay to use ui related name here such as
 * isLoading or screen message
 */
internal class LoginDestinationViewModel(
    val loginController: LoginController,
    val registerController: RegisterController
) {
    val isLoading: Flow<Boolean> =
        combine(loginController.isLogging, registerController.isRegistering)
        { isLogging, isRegistering ->
            isLogging || isRegistering
        }
    val screenMessage: Flow<String?> =
        combine(loginController.errorMessage, registerController.errorMessage)
        { loginMsg, registerMsg ->
            loginMsg ?: registerMsg
        }
}