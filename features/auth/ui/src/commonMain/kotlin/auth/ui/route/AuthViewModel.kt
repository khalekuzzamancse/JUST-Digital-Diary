package auth.ui.route

import auth.domain.login.repoisitory.LoginRepository
import auth.domain.register.repository.RegisterRepository
import auth.ui.login.components.event.LoginEvent
import auth.ui.login.route.LoginDestinationViewModel
import auth.ui.register.route.RegisterDestinationViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
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
    private val loginNRegisterState: Flow<AuthScreenState> =
        combine(loginViewModel.state, registerViewModel.state) { loginState, registerState ->
            AuthScreenState(
                showProgressBar = loginState.isLoading || registerState.isLoading,
                snackBarData = loginState.snackBarData ?: registerState.snackBarData
            )
        }

    init {
        CoroutineScope(Dispatchers.Default).launch {
            loginNRegisterState.collect { state ->
                _uiState.update { state }
            }
        }
    }

    suspend fun login(): LoginEvent.LoginDestinationEvent.LoginSuccess? {
        return loginViewModel.login()
    }

    suspend fun register(): Boolean {
        return registerViewModel.register()
    }

    fun closeSnackBar() {
        _uiState.update {
            it.copy(snackBarData = null)
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


}
