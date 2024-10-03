package just.digitaldiary// TokenViewModel.kt

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

// TokenViewModel.kt

import androidx.lifecycle.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class TokenViewModel(application: Application) : AndroidViewModel(application) {
    private val tokenManager = TokenManager(application)

    // StateFlow to hold the token state
    private val _token = MutableStateFlow<String?>(null)
    val token: StateFlow<String?> = _token.asStateFlow()

    // StateFlow to represent loading state
    private val _isTokenLoaded = MutableStateFlow(false)
    val isTokenLoaded: StateFlow<Boolean> = _isTokenLoaded.asStateFlow()

    init {
        // Initialize the token
        viewModelScope.launch {
            val tokenValue = tokenManager.tokenFlow.firstOrNull()
            _token.value = tokenValue
            _isTokenLoaded.value = true // Token loading is complete
        }
    }

    fun saveToken(token: String) {
        viewModelScope.launch {
            tokenManager.saveToken(token)
            _token.value = token
        }
    }

    fun clearToken() {
        viewModelScope.launch {
            tokenManager.clearToken()
            _token.value = null
        }
    }
}
