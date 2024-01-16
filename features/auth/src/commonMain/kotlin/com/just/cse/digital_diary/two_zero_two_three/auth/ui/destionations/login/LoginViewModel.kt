package com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.login

import com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.login.form.LoginFormData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
  private val  onLoginSuccess:()->Unit,
) {
    private val scope: CoroutineScope= CoroutineScope(Dispatchers.Default)

    private val _showProcessBar = MutableStateFlow(false)
    val showProcessBar = _showProcessBar.asStateFlow()
    private val _screenMessage = MutableStateFlow<String?>(null)
    val screenMessage = _screenMessage.asStateFlow()
    private fun updateScreenMessage(msg: String) {
        scope.launch {
            _screenMessage.value = msg
            delay(1000)
            clearScreenMessage()
        }
    }

    private fun clearScreenMessage() {
        _screenMessage.value = null
    }

    companion object {
        private const val TAG = "LoginViewModel: "
        fun log(message: String) {
            println("$TAG$message")
        }
    }

    init {
        scope.launch {
            screenMessage.collect { msg ->
                if (msg != null) {
                    log(msg)

                }
            }
        }
    }

     fun  onLoginRequest() {
         scope.launch {
             _showProcessBar.value=true
             delay(2000)
             val success = data.value.username.trim().trimEnd() == "abc" &&data.value. password.trim().trimEnd() == "123"
             if (success) {
                 loginSuccess()
             } else {
                 onLoginFailure()
             }
             _showProcessBar.value=false

         }

    }
    private fun loginSuccess(){
        scope.launch {
            updateScreenMessage("Login Success")
            delay(1000)
            onLoginSuccess()
        }


    }
    private fun onLoginFailure(){
        updateScreenMessage("Login failed,put username=abc,password=123")
    }

    private val _data = MutableStateFlow(
        LoginFormData(
            username = "abc", password = "123"
        )
    )
    val data=_data.asStateFlow()
    fun onUserNameChanged(username: String) {
        _data.update { it.copy(username = username) }
    }
    fun onPasswordChanged(password: String) {
        _data.update { it.copy(password = password) }
    }


}

