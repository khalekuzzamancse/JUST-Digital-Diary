package auth.ui

import androidx.lifecycle.ViewModel
import auth.presentationlogic.controller.LoginController
import auth.presentationlogic.controller.PasswordResetController
import auth.presentationlogic.controller.RegisterController
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

/**
 * - View model is UI related to is it okay to use ui related name here such as
 * isLoading or screen message
 */
internal class AuthViewModel(
    val loginController: LoginController,
    val registerController: RegisterController,
    val resetController: PasswordResetController
) : ViewModel() {
    val isLoading: Flow<Boolean> =
        combine(
            loginController.isLogging,
            registerController.isRegistering,
            resetController.isRequestSending
        )
        { isLogging, isRegistering, isResetSending ->
            isLogging || isRegistering || isResetSending
        }
    val screenMessage: Flow<String?> =
        combine(
            loginController.errorMessage,
            registerController.errorMessage,
            resetController.errorMessage
        )
        { loginMsg, registerMsg ,resetMsg->
            loginMsg ?: registerMsg?:resetMsg
        }
}