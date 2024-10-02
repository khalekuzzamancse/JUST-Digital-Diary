package navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    onEvent: (AppEvent) -> Unit,
) {
    val mainViewModel = viewModel { MainViewModel() }
    val slapScreenShowing = mainViewModel.showSlapScreen.collectAsState().value
    val token = mainViewModel.token.collectAsState(null).value
    val scope = rememberCoroutineScope()
    AppTheme {
        if (mainViewModel.isTokenRefreshing.collectAsState().value) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }

        } else {
            if (token == null) {
                AuthRoute(
                    onLoginSuccess = mainViewModel::onLoginSuccess
                )
            } else {
                _FeatureNavGraph(viewModel = mainViewModel, onEvent = onEvent)
            }
        }


    }
}


@Composable
private fun _FeatureNavGraph(
    viewModel: MainViewModel,
    onEvent: (AppEvent) -> Unit,
) {
    val navHostController = rememberNavController()
    val navigator = remember { Navigator(navHostController, onEvent) }
    var isNavRailMode by remember { mutableStateOf(false) }
    NavItemDecorator(
        controller = viewModel.controller,
        onLogOutRequest = viewModel::logOut,
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


