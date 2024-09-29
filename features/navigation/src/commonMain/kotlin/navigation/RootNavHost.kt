package navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import auth.ui.AuthRoute
import common.newui.Destination
import common.newui.DrawerToNavRailDecorator
import common.newui.NavigationController
import common.newui.NavigationEvent
import navigation.component.DrawerHeader
import navigation.component.NavDestination
import navigation.component.NavDestinationBuilder


@Composable
fun RootNavHost(
    onEvent: (AppEvent) -> Unit,
) {
    val mainViewModel = viewModel { MainViewModel() }
    val slapScreenShowing = mainViewModel.showSlapScreen.collectAsState().value
    val token = NavigationFactory.token.collectAsState().value
    AppTheme {
        if (token == null) {
            AuthRoute(
                onLoginSuccess = {
                    NavigationFactory.updateToken(it)
                }
            )
        } else {
            _FeatureNavGraph(viewModel = mainViewModel, onEvent = onEvent)
        }


    }
}


@Composable
private fun _FeatureNavGraph(
    viewModel: MainViewModel,
    onEvent: (AppEvent) -> Unit,
) {
    val navHostController = rememberNavController()
    val featureNavGraphController = remember { MainNavGraphController(navHostController, onEvent) }
    NavigationHost(
        controller = viewModel.controller,
        onLogOutRequest = viewModel::logOut,
        onSelectionRequest = { destination ->
            //notify the controller that a destination is selected
            //so that it highlight it and close the drawer(if needed)
            viewModel.select(destination)
            featureNavGraphController.navigator(destination)
        },
        contentHost = {
            RootNavGraph(
                onEvent = onEvent,
                openDrawerRequest = viewModel::openDrawer,
                navController = navHostController,
                onBackPressed = featureNavGraphController::pop,
                startDestination = GraphRoutes.TOPMOST_OTHER_FEATURE,
                isNavRailMode = false,
            )
        }
    )
}

@Composable
fun NavigationHost(
    controller: NavigationController,
    onSelectionRequest: (Destination) -> Unit,
    onLogOutRequest: () -> Unit = {},
    contentHost: @Composable () -> Unit,
) {
    DrawerToNavRailDecorator(
        groups = NavDestinationBuilder.navGroups,
        controller = controller,
        onEvent = { event ->
            if (event is NavigationEvent.Selected) {
                onSelectionRequest(event.destination)
            }
        },
        header = {
            DrawerHeader(
                onLogOutRequest = onLogOutRequest
            )
        },
        content = contentHost
    )

}


