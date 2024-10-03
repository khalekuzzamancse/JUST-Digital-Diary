package navigation

import androidx.lifecycle.ViewModel
import navigation.component.NavDestination

class MainViewModel : ViewModel() {
    val controller = NavigationDrawerController()
    fun openDrawer() = controller.openDrawer()

    init {
        controller.select(NavDestination.Home)
        //The root is home, if the open open successfully then it is guaranteed that we are at home
        //If apps fail to open then we are not at home,since the app is open so we are at home,so need not to check
        //that we navigation was successful or not is case of home

    }

    fun select(destination: Destination) = controller.select(destination)

}