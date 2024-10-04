package navigation

import androidx.lifecycle.ViewModel
import navigation.component.NavDestination

class MainViewModel : ViewModel() {
    val controller = NavigationDrawerController()
    fun openDrawer() = controller.openDrawer()

    fun select(destination: Destination) = controller.select(destination)

}