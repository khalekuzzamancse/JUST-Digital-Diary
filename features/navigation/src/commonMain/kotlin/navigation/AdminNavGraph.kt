package navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.createGraph

object AdminRoute {


}


fun NavController.adminNavGraph(): NavGraph {

    return createGraph(startDestination = "") {

    }
}