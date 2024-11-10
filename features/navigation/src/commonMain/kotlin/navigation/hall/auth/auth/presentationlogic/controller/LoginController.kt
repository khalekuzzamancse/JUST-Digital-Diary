package navigation.hall.auth.auth.presentationlogic.controller

import navigation.hall.auth.auth.presentationlogic.model.LoginModel
import kotlinx.coroutines.flow.StateFlow

/**
 * - On Login success should return true so that
 * parent can navigate or pop this route
 * - For more information
 *   - see [ControllerDocs]
 * @property isLogging is denote is loading or not, try to keep ui independent name
 */
interface LoginController {
    val isLogging: StateFlow<Boolean>
    val errorMessage: StateFlow<String?>
    val state: StateFlow<LoginModel>
    val validator: Validator

    fun onUserNameChanged(value: String)
    fun onPasswordChanged(value: String)

    /**
     * @return is login as admin or student
     */
    suspend fun performLogin(): Boolean
    interface Validator {
        val areAllFieldsFilled: StateFlow<Boolean>
        val errors: StateFlow<List<String>>
        fun observeFieldChanges(input: StateFlow<LoginModel>)

    }


}