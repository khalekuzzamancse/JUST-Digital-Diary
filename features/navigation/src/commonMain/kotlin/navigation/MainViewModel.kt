package navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import common.ui.Destination
import common.ui.NavigationController
import core.database.apis.TokenApi
import core.database.factory.createTokenApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
) : ViewModel() {
    private val _showSlapScreen = MutableStateFlow(true)
    val showSlapScreen = _showSlapScreen.asStateFlow()
    val controller = NavigationController()
    val token = MutableStateFlow<String?>(null)
    private val _isTokenRefreshing = MutableStateFlow(false)
    val isTokenRefreshing = _isTokenRefreshing.asStateFlow()

    init {
        viewModelScope.launch {
//            api.getToken().collect{
//                println("TokenVmTokenInti:$it")
//            }
        }
    }

    fun onLoginSuccess(token: String) {
        viewModelScope.launch {
//            _isTokenRefreshing.update { true }
//            api.saveToken(token).fold(
//                onSuccess = {
//                    println("TokenVm:Saved")
//                },
//                onFailure = {
//                    println("TokenVM:$it")
//
//                }
//            )
//            _isTokenRefreshing.update { false }
      }

    }

    init {
        viewModelScope.launch {
            token.collect {
                println("TokenViewModel:$it")
                NavigationFactory.updateToken(it)
            }
        }

    }

    fun logOut() {
        viewModelScope.launch {
            _isTokenRefreshing.update { true }
            // api.clearToken()
            _isTokenRefreshing.update { false }
        }
    }

    fun openDrawer() = controller.openDrawer()
    fun select(destination: Destination) = controller.select(destination)

}