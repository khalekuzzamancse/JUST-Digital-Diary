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
import common.ui.ConfirmDialog
import kotlinx.coroutines.launch
import miscellaneous.MiscFeatureEvent
import navigation.component.DrawerHeader
import navigation.component.NavDestination
import navigation.component.NavDestinationBuilder
import navigation.navgraph.NavGraph

//
//@Composable
//fun RootNavHost(
//    token: String?,
//    onTokenSaveRequest: (String) -> Unit = {},
//    onTokenDeleteRequest: () -> Unit = {},
//    onEvent: (AppEvent) -> Unit,
//) {
//   AnimateDrawerPreview()
//    val mainViewModel = viewModel { MainViewModel() }
//    _FeatureNavGraph(
//        viewModel = mainViewModel,
//        onEvent = onEvent,
//        onLogOutRequest = {
//            onTokenDeleteRequest()
//        }
//    )
//}

@Composable
fun RootNavHost(
    token: String?,
    onTokenSaveRequest: (String) -> Unit = {},
    onTokenDeleteRequest: () -> Unit = {},
    onEvent: (AppEvent) -> Unit,
) {
    val mainViewModel = viewModel { MainViewModel() }
    var showConfirmDialog by remember { mutableStateOf(false) }

    if (showConfirmDialog) {
        ConfirmDialog(
            message = "Are you sure to visit website?",
            onDismissRequest = {
                showConfirmDialog = false
            },
            onConfirm = {
                showConfirmDialog = false
                onEvent(
                    AppEvent.WebVisitRequest("  https://just.edu.bd/")
                )
            }
        )
    }

    LaunchedEffect(token) {
        NavigationFactory.updateToken(token)
    }

    AppTheme {
        if (token == null) {
            AuthRoute(
                onLoginSuccess = {
                    //println(it)
                    onTokenSaveRequest(it)
                }
            )
        } else {
            _FeatureNavGraph(
                viewModel = mainViewModel,
                onEvent = { event ->
                    if (event is AppEvent.WebVisitRequest)
                        showConfirmDialog = true
                    else onEvent(event)
                },
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
                onNavigateBackRequest = navigator::pop,
                startDestination = NavDestination.Home.route,
                isNavRailMode = isNavRailMode,
                navController = navController,
                onMiscFeatureEvent = navigator::onMiscFeatureEvent,
                navigateToProfile = { navigator.navigator(NavDestination.Profile) },
                onAdminEvent = navigator::onAdminEvent
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


