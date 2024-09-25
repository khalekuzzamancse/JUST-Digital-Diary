package auth.ui.login

import auth.model.LoginModel
import kotlinx.coroutines.flow.StateFlow

/**
 * - On Login success should return true so that
 * parent can navigate or pop this route
 * @property isLogging is denote is loading or not, try to keep ui independent name
 */
interface LoginController {
    val isLogging: StateFlow<Boolean>
    val errorMessage: StateFlow<String?>
    val state:StateFlow<LoginModel>
    fun onUserNameChanged(value:String)
    fun onPasswordChanged(value:String)
   suspend fun performLogin():Boolean
}