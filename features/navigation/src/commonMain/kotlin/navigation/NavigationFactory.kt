@file:Suppress("FunctionName")

package navigation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

internal object NavigationFactory {
    private val _token = MutableStateFlow<String?>(null)
    val token = _token.asStateFlow()
    fun updateToken(token: String?) {
     _token.update { token }
    }

}