//package com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.login
//
//import androidx.compose.material3.DrawerState
//import androidx.compose.material3.DrawerValue
//import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
//import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.ui.Modifier
//import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.NavigationRailLayout
//import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.modal_drawer.ModalDrawerGroup
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.launch
//
//class LoginLayoutFactoryState(
//    private val scope: CoroutineScope,
//    private val destination: List<NavigationItem>,
//    private val onNavigationRequest: (String) -> Unit,
//) {
//    val drawerState = DrawerState(DrawerValue.Closed)
//    fun openDrawer() {
//        scope.launch {
//            drawerState.open()
//        }
//    }
//
//    val drawerGroups = listOf(ModalDrawerGroup("", destination))
//
//    fun closeDrawer() {
//        scope.launch {
//            drawerState.close()
//        }
//    }
//
//    fun onDrawerItemClick(route: String) {
//        onNavigationRequest(route)
//    }
//
//    fun onNavRailItemSelected(item: Int) {
//        destination.getOrNull(item)?.let {
//            onNavigationRequest(it.route)
//        }
//    }
//}
//
////stateful
//@Composable
//fun LoginLayoutFactory(
//    factory: LoginLayoutFactoryState,
//    destination: List<NavigationItem>,
//    snackbarMessage: String? = null,
//    headerSection: @Composable ((Modifier) -> Unit),
//    loginSection: @Composable ((Modifier) -> Unit),
//) {
//    LoginLayoutFactory(
//        drawerGroups = factory.drawerGroups,
//        destination = destination,
//        drawerState = factory.drawerState,
//        onDrawerItemClick = factory::onDrawerItemClick,
//        onDrawerOpenRequest = factory::openDrawer,
//        onDrawerCloseRequested = factory::closeDrawer,
//        onNavRailItemSelected = factory::onNavRailItemSelected,
//        snackbarMessage = snackbarMessage,
//        headerSection = headerSection,
//        loginSection = loginSection
//    )
//}
//
////Stateless
//@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
//@Composable
//fun LoginLayoutFactory(
//    drawerState: DrawerState,
//    onDrawerItemClick: (String) -> Unit,
//    onDrawerCloseRequested: () -> Unit,
//    onDrawerOpenRequest: () -> Unit,
//    onNavRailItemSelected: (Int) -> Unit,
//    destination: List<NavigationItem>,
//    drawerGroups: List<ModalDrawerGroup>,
//    snackbarMessage: String? = null,
//    headerSection: @Composable ((Modifier) -> Unit),
//    loginSection: @Composable ((Modifier) -> Unit)
//) {
//    when (calculateWindowSizeClass().widthSizeClass) {
//        medium, expanded -> {
//            NavigationRailLayout(
//                selectedDestinationIndex = 0,
//                destinations = destination,
//                onNavRailItemSelected = onNavRailItemSelected,
//            ) {
//                LoginLayoutNonCompactScreen(
//                    headerSection = headerSection,
//                    loginSection = loginSection,
//                )
//            }
//        }
//
//        compact -> {
//            LoginLayoutOnCompactScreen(
//                headerSection = headerSection,
//                loginSection = loginSection,
//                modifier = Modifier,
//                destination = drawerGroups,
//                drawerState = drawerState,
//                onDrawerItemClick = onDrawerItemClick,
//                onDrawerCloseRequested = onDrawerCloseRequested,
//                onDrawerOpenRequest = onDrawerOpenRequest,
//                snackbarMessage = snackbarMessage
//            )
//        }
//
//    }
//}
//
//
