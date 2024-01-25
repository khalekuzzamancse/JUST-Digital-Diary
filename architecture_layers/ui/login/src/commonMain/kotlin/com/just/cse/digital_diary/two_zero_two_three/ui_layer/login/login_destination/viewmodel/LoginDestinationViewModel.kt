package com.just.cse.digital_diary.two_zero_two_three.ui_layer.login.login_destination.viewmodel

import com.just.cse.digital_diary.two_zero_two_three.domain_layer.login.model.LoginRequestModel
import com.just.cse.digital_diary.two_zero_two_three.domain_layer.login.model.LoginResponseModel
import com.just.cse.digital_diary.two_zero_two_three.domain_layer.login.repoisitory.LoginRepository
import com.just.cse.digital_diary.two_zero_two_three.ui_layer.login.components.controls.LoginControlsLoginModuleEvent
import com.just.cse.digital_diary.two_zero_two_three.ui_layer.login.components.form.LoginFormManager
import com.just.cse.digital_diary.two_zero_two_three.ui_layer.login.login_destination.event.DestinationLoginModuleEvent
import com.just.cse.digital_diary.two_zero_two_three.ui_layer.login.login_destination.event.LoginModuleEvent
import com.just.cse.digital_diary.two_zero_two_three.ui_layer.login.login_destination.states.LoginDestinationState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginDestinationViewModel(
    private val repository: LoginRepository,
) {

    companion object{
        private const val TAG="LoginDestinationViewModel : "
        private fun log(message: String){
            println("$TAG$message")
        }
    }
    private val scope= CoroutineScope(Dispatchers.Default)
    private val _state = MutableStateFlow(LoginDestinationState.toEmpty())
    val state = _state.asStateFlow()
    private val formManager= LoginFormManager()
    val formEvent =formManager.event
    val formData = formManager.data
    var onRegisterDestinationOpenRequest:()->Unit={}

    fun onEvent(loginModuleEvent: LoginModuleEvent) {
        when (loginModuleEvent) {
            is LoginControlsLoginModuleEvent -> onControlsEvent(loginModuleEvent)
            DestinationLoginModuleEvent.ExitRequest -> onExitRequest()
        }
    }


    private fun onControlsEvent(event: LoginControlsLoginModuleEvent) {
        when (event) {
            LoginControlsLoginModuleEvent.LoginModuleRequest -> onLoginRequest()
            LoginControlsLoginModuleEvent.RegisterRequest -> onRegisterDestinationOpenRequest()
            LoginControlsLoginModuleEvent.PasswordResetRequest -> {}
        }

    }


    private fun onLoginRequest() {
        scope.launch{
            startLoading()
            val username=formData.value.username
            val password=formData.value.password
           val response= repository.login(
                LoginRequestModel(username = username, password = password)
            )
            if(response is LoginResponseModel.Success){
               onLoginSuccess()

            }
            else if (response is LoginResponseModel.Failure){
                onLoginFailure(response.reason)
            }
        }

    }
    private suspend fun onLoginFailure(reason:String?){
        stopLoading()
        updateSnackBarMessage("Login failed because of $reason")
        delay(1500)
        clearMessages()
        _state.update { it.copy(isLoginSuccess = false) }
    }
    private suspend fun onLoginSuccess(){
        stopLoading()
        updateSnackBarMessage("Login Successful")
        delay(1500)
        clearMessages()
        _state.update { it.copy(isLoginSuccess = true) }
    }
    private fun clearMessages(){
        updateSnackBarMessage(null)
    }
    private fun updateSnackBarMessage(message: String?){
        _state.update { it.copy(message = message) }
    }
    private fun startLoading() {
        _state.update { it.copy(isLoading = true) }
    }
    private fun stopLoading() {
        _state.update { it.copy(isLoading = false) }
    }

    private fun onExitRequest() {

    }

}