package navigation

import androidx.lifecycle.ViewModel
import common.ui.Destination
import common.ui.NavigationController

class MainViewModel : ViewModel() {
    val controller = NavigationController()
    fun openDrawer() = controller.openDrawer()
    fun select(destination: Destination) = controller.select(destination)

}