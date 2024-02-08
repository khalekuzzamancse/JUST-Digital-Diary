package com.just.cse.digital_diary.two_zero_two_three.auth.viewmodel

import com.just.cse.digital_diary.two_zero_two_three.auth.functionalities.register.viewmodel.RegisterDestinationViewModel
import com.just.cse.digital_diary.two_zero_two_three.auth.state.AuthScreenState
import com.just.cse.digital_diary.two_zero_two_three.domain.register.repository.RegisterRepository
import com.just.cse.digital_diary.two_zero_two_three.domain_layer.login.repoisitory.LoginRepository
import com.just.cse.digital_diary.two_zero_two_three.ui_layer.login.event.LoginEvent
import com.just.cse.digital_diary.two_zero_two_three.auth.functionalities.login.viewmodel.LoginDestinationViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel(
    loginRepository: LoginRepository,
    registrationRepository: RegisterRepository
) {
    private val _uiState = MutableStateFlow(AuthScreenState())
    val uiState = _uiState.asStateFlow()
    val loginViewModel = LoginDestinationViewModel(
        repository = loginRepository,
    )
    val registerViewModel = RegisterDestinationViewModel(registrationRepository)

    suspend fun login():LoginEvent.LoginDestinationEvent.LoginSuccess?{
        return loginViewModel.login()
    }
    suspend fun register():Boolean{
        return registerViewModel.register()
    }


    private suspend fun observeRegisterDestinationState() {
        registerViewModel.state.collect { registerDestinationState ->
            _uiState.update {
                it.copy(
                    showProgressBar = registerDestinationState.isLoading,
                    snackBarMessage = registerDestinationState.message,
                )
            }
        }

    }


    init {
        //observers parallel
        CoroutineScope(Dispatchers.Default).launch {
            observeLoginState()
        }

        CoroutineScope(Dispatchers.Default).launch {
            observeRegisterDestinationState()
        }


    }

     fun openRegisterDestination() {
        registerViewModel.clearState()
        _uiState.update {
            it.copy(showRegisterForm = true)
        }
    }

     fun closeRegistrationDestination() {
        _uiState.update {
            it.copy(showRegisterForm = false)
        }
    }

    private suspend fun observeLoginState() {
        loginViewModel.state.collect { loginState ->
            _uiState.update {
                it.copy(
                    showProgressBar = loginState.isLoading,
                    snackBarMessage = loginState.message,
                )
            }

        }

    }


}