package navigation

import androidx.lifecycle.ViewModel
import common.newui.Destination
import common.newui.NavigationController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _showSlapScreen = MutableStateFlow(true)
    val showSlapScreen = _showSlapScreen.asStateFlow()
    val controller = NavigationController()
    fun logOut() {
       // AuthComponentProvider.signInOut()
    }

    fun openDrawer() = controller.openDrawer()
    fun select(destination: Destination) = controller.select(destination)

}