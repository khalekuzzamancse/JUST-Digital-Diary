package navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import auth.ui.AuthRoute
import common.ui.Destination
import common.ui.DrawerToNavRailDecorator
import common.ui.NavigationController
import common.ui.NavigationEvent
import navigation.component.DrawerHeader
import navigation.component.NavDestinationBuilder


@Composable
fun RootNavHost(
    token: String?,
    onEvent: (AppEvent) -> Unit,
) {
    val mainViewModel = viewModel { MainViewModel() }
    LaunchedEffect(token) {
        NavigationFactory.updateToken(token)
    }

    AppTheme {
        if (token == null) {
            AuthRoute(
                onLoginSuccess = {
                    onEvent(AppEvent.LoginSuccess(it))
                }
            )
        } else {
            _FeatureNavGraph(
                viewModel = mainViewModel,
                onEvent = onEvent,
                onLogOutRequest = {
                    onEvent(AppEvent.LogOut)
                }
            )
        }


    }
}


@Composable
private fun _FeatureNavGraph(
    viewModel: MainViewModel,
    onLogOutRequest: () -> Unit,
    onEvent: (AppEvent) -> Unit,
) {
    val navHostController = rememberNavController()
    val navigator = remember { Navigator(navHostController, onEvent) }
    var isNavRailMode by remember { mutableStateOf(false) }
    NavItemDecorator(
        controller = viewModel.controller,
        onLogOutRequest = onLogOutRequest,
        onSelectionRequest = { destination ->
            //notify the controller that a destination is selected
            //so that it highlight it and close the drawer(if needed)
            viewModel.select(destination)
            navigator.navigator(destination)
        },
        onNavModeChange = {
            isNavRailMode = it
        },
        contentHost = {
            NavGraph(
                onEvent = onEvent,
                openDrawerRequest = viewModel::openDrawer,
                onBackPressed = navigator::pop,
                startDestination = GraphRoutes.HOME,
                isNavRailMode = isNavRailMode,
                navController = navHostController,
                onMiscFeatureEvent = navigator::onMiscFeatureEvent
            )
        }
    )
}

@Composable
fun NavItemDecorator(
    controller: NavigationController,
    onSelectionRequest: (Destination) -> Unit,
    onNavModeChange: (isNavRailMode: Boolean) -> Unit,
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
            if (event is NavigationEvent.NavRailNavigationMode)
                onNavModeChange(true)
            if (event is NavigationEvent.DrawerNavigationMode)
                onNavModeChange(false)
        },
        header = {
            DrawerHeader(
                onLogOutRequest = onLogOutRequest
            )
        },
        content = contentHost
    )

}


