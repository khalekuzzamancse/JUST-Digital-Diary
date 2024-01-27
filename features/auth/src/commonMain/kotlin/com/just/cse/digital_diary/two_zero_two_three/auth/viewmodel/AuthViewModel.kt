package com.just.cse.digital_diary.two_zero_two_three.auth.viewmodel

import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.register.destination.viewmodel.RegisterDestinationViewModel
import com.just.cse.digital_diary.two_zero_two_three.auth.state.AuthScreenState
import com.just.cse.digital_diary.two_zero_two_three.data_layer.login.repository.LoginRepositoryImpl
import com.just.cse.digital_diary.two_zero_two_three.data_layer.register.repoisitory.RegisterRepositoryImpl
import com.just.cse.digital_diary.two_zero_two_three.ui_layer.login.login_destination.viewmodel.LoginDestinationViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel {
    private val _uiState = MutableStateFlow(AuthScreenState())
    val uiState = _uiState.asStateFlow()
    val loginViewModel = LoginDestinationViewModel(
        repository = LoginRepositoryImpl(),
    )
    val registerViewModel: RegisterDestinationViewModel =
        RegisterDestinationViewModel(RegisterRepositoryImpl())
    val loginSucess = loginViewModel.shouldExit


    init {
        loginViewModel.onRegisterDestinationOpenRequest = {
            openRegisterDestination()
        }

    }

    fun onRegisterDestinationExitRequest() {
        closeRegistrationDestination()
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

    private fun openRegisterDestination() {
        registerViewModel.clearState()
        _uiState.update {
            it.copy(showRegisterForm = true)
        }
    }

    private fun closeRegistrationDestination() {
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