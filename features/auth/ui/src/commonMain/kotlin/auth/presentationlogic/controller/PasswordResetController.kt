package auth.presentationlogic.controller

import kotlinx.coroutines.flow.StateFlow

interface PasswordResetController {
        val isRequestSending: StateFlow<Boolean>
        val errorMessage: StateFlow<String?>
        val email: StateFlow<String>
        val newPassword: StateFlow<String>
        val code: StateFlow<String>
        fun onEmailChanged(value: String)
        fun  onPasswordChanged(value: String)
        fun onCodeChanged(value: String)
        val isSendResetRequestSuccessful: StateFlow<Boolean>
        suspend fun sendResetRequest()
        suspend fun resetPassword()
    }