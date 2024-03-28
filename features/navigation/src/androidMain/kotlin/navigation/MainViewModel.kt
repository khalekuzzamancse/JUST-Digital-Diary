package navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import auth.di.AuthComponentProvider
import common.newui.Destination
import common.newui.NavigationController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _showSlapScreen = MutableStateFlow(true)
    val showSlapScreen = _showSlapScreen.asStateFlow()
    val controller = NavigationController()
    val isSignedIn = combine(
        AuthComponentProvider.isSingedIn,
        AuthComponentProvider.observeSignIn()
    ) { alreadySignIn, newLogin ->
        alreadySignIn || newLogin
    }

    fun logOut() {
        AuthComponentProvider.signInOut()
    }

    private fun stopSlapScreen() {
        _showSlapScreen.update { false }
    }

    fun onLoginSuccess(username: String, password: String) {
        CoroutineScope(Dispatchers.Default).launch {
            AuthComponentProvider.saveSignInInfo(username, password)
        }
    }

    init {
        CoroutineScope(Dispatchers.Default).launch {
            startObserverSignIn()
        }
        CoroutineScope(Dispatchers.Default).launch {
            delay(4000)
            stopSlapScreen()
        }
    }

    private suspend fun startObserverSignIn() {
        isSignedIn.collect { signIn ->
            if (signIn)
                AuthComponentProvider.updateAuthToken()
        }
    }

    fun openDrawer() = controller.openDrawer()
    fun select(destination: Destination) = controller.select(destination)

}