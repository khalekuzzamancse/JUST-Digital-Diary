package auth.ui.route

import auth.domain.login.repoisitory.LoginRepository
import auth.domain.register.repository.RegisterRepository
import auth.ui.login.components.event.LoginEvent
import auth.ui.login.route.LoginDestinationViewModel
import auth.ui.register.route.RegisterDestinationViewModel
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

    suspend fun login(): LoginEvent.LoginDestinationEvent.LoginSuccess?{
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
                    snackBarData = loginState.snackBarData
                )
            }

        }

    }


}