package navigation

import androidx.lifecycle.ViewModel
import common.ui.Destination
import common.ui.NavigationController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

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