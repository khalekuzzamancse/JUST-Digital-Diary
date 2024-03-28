package navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import auth.ui.AuthNavHost
import navigation.themes.AppTheme


@SuppressLint("ComposableNaming")
@Composable
fun AndroidRootNavigation(
    onEvent: (AppEvent) -> Unit,
) {
    val mainViewModel = viewModel { MainViewModel() }
    val slapScreenShowing = mainViewModel.showSlapScreen.collectAsState().value
    if (slapScreenShowing)
        SplashScreen()
    else
        _FeatureSection(mainViewModel, onEvent)
}

@SuppressLint("ComposableNaming")
@Composable
private fun _FeatureSection(
    viewModel: MainViewModel,
    onEvent: (AppEvent) -> Unit,
) {
    val isSingedIn = viewModel.isSignedIn.collectAsState(false).value
    AppTheme {
        if (isSingedIn)
            _FeatureNavGraph(viewModel = viewModel, onEvent = onEvent)
        else
            AuthNavHost(onLoginSuccess = viewModel::onLoginSuccess)

    }

}

@SuppressLint("ComposableNaming")
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

