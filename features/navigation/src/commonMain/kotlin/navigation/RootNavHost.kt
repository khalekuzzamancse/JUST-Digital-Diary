package navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import auth.ui.AuthRoute
import kotlinx.coroutines.launch
import navigation.component.DrawerHeader
import navigation.component.NavDestination
import navigation.component.NavDestinationBuilder

@Composable
fun RootNavHost(
    token: String?,
    onTokenSaveRequest: (String) -> Unit = {},
    onTokenDeleteRequest: () -> Unit = {},
    onEvent: (AppEvent) -> Unit,
) {


    val mainViewModel = viewModel { MainViewModel() }
    _FeatureNavGraph(
        viewModel = mainViewModel,
        onEvent = onEvent,
        onLogOutRequest = {
            onTokenDeleteRequest()
        }
    )
}

@Composable
private fun _RootNavHost(
    token: String?,
    onTokenSaveRequest: (String) -> Unit = {},
    onTokenDeleteRequest: () -> Unit = {},
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
                    onTokenSaveRequest(it)
                }
            )
        } else {
            _FeatureNavGraph(
                viewModel = mainViewModel,
                onEvent = onEvent,
                onLogOutRequest = {
                    onTokenDeleteRequest()
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
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()

    val navigator = remember { Navigator(navController, onEvent) }
    var isNavRailMode by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        navController.currentBackStack.collect { entries ->
            val lastDestinationRoute =
                entries.lastOrNull { entry -> entry.destination.route != null }?.destination?.route
            val selected: Destination? =
                NavDestinationBuilder.allDestinations.find { it.route == lastDestinationRoute }
            if (selected != null)
                viewModel.select(selected)
            else
                viewModel.select(Destination.None)

        }
    }


    NavItemDecorator(
        controller = viewModel.controller,
        onLogOutRequest = onLogOutRequest,
        onSelectionRequest = { destination ->
            scope.launch {
                navigator.navigator(destination)
            }

        },
        onNavModeChange = {
            isNavRailMode = it
        },
        contentHost = {
            NavGraph(
                onEvent = onEvent,
                openDrawerRequest = viewModel::openDrawer,
                onBackPressed = navigator::pop,
                startDestination = NavDestination.Home.route,
                isNavRailMode = isNavRailMode,
                navController = navController,
                onMiscFeatureEvent = navigator::onMiscFeatureEvent,
                navigateToProfile = { navigator.navigator(NavDestination.Profile) }
            )
        }
    )
}

@Composable
fun NavItemDecorator(
    controller: NavigationDrawerController,
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


