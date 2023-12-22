package com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.LocationSearching
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Note
import androidx.compose.material.icons.filled.Person4
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.IndeterminateCircularIndicator
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.modal_drawer.ModalDrawerGroup
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


val homeDestinations = listOf(
    NavigationItem(
        label = "Menu",
        icon = Icons.Default.Menu
    ),
    NavigationItem(
        label = "Factually Members",
        icon = Icons.Default.Person4
    ),
    NavigationItem(
        label = "Admin Office",
        icon = Icons.Default.Settings
    ),
    NavigationItem(
        label = "Vehicle Tracking",
        icon = Icons.Default.LocationSearching
    ),
    NavigationItem(
        label = "Daily Diary",
        icon = Icons.Default.Note
    ),
    NavigationItem(
        label = "Daily Diary",
        icon = Icons.Default.CalendarMonth
    ),
    NavigationItem(
        label = "Explore JUST",
        icon = Icons.Default.Explore
    )
)

object HomeDrawerItems {
    private val group = ModalDrawerGroup(
        name = "",
        members = homeDestinations
    )
    val drawerGroups = listOf(group)
}
class HomeScreenState(
    private val scope: CoroutineScope,
) {
    val drawerState = DrawerState(DrawerValue.Closed)
    fun onOpenDrawerRequest() {
        scope.launch {
            drawerState.open()
        }
    }

    fun onCloseDrawerRequest() {
        scope.launch {
            drawerState.close()
        }
    }

    //
    private val _snackbarMessage = MutableStateFlow<String?>("Welcome")
    val snackbarMessage = _snackbarMessage.asStateFlow()
    private val _showProgressBar = MutableStateFlow(false)
    val showProgressBar = _showProgressBar.asStateFlow()
    fun onLoginRequest() {
        scope.launch {
            _showProgressBar.value = true
            delay(2_000)
            _snackbarMessage.value = "Not Implemented Yet..."
            _showProgressBar.value = false
        }

    }

}



@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun Demo() {
    val isNotCompactScreen =
        calculateWindowSizeClass().widthSizeClass != WindowWidthSizeClass.Compact
    val screenType = calculateWindowSizeClass().widthSizeClass

    var navRailState by remember {
        mutableStateOf(
            NavigationRailState(
                options = homeDestinations,
                selectedItem = 0,
                isExpandMode = false
            )
        )
    }
    val onNavRailItemSelected: (Int) -> Unit = { selectedItemIndex ->
        val expanded = navRailState.isExpandMode
        navRailState = navRailState.copy(
            isExpandMode = if (selectedItemIndex == 0) !expanded else expanded,
            selectedItem = selectedItemIndex
        )
    }
    val scope = rememberCoroutineScope()
    val uiState = remember {
        HomeScreenState(scope)
    }

    LoginScreenScreen(
        uiState = uiState,
        screenWidthType = screenType,
        navigationSection = { modifier ->
            NavigationRails(
                state = navRailState,
                onSelectionChanged = onNavRailItemSelected
            )
        },
        headerSection = { modifier ->
            LoginSectionHeader(
                modifier = modifier
                //.align(Alignment.CenterHorizontally)
            )
        },
        loginSection = { modifier ->
            LoginFieldsNControls(
                modifier = modifier,
                isHorizontal = isNotCompactScreen,
                onRegisterRequest = uiState::onLoginRequest,
                onLoginRequest = uiState::onLoginRequest,
                onPasswordResetRequest =uiState::onLoginRequest
            )
        },
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreenScreen(
    uiState:HomeScreenState,
    screenWidthType: WindowWidthSizeClass,
    headerSection: @Composable ((Modifier) -> Unit),
    navigationSection: @Composable ((Modifier) -> Unit),
    loginSection: @Composable ((Modifier) -> Unit)
) {
    val isMedium = screenWidthType == WindowWidthSizeClass.Medium
    val isExpanded = screenWidthType == WindowWidthSizeClass.Expanded
    val isCompact = screenWidthType == WindowWidthSizeClass.Compact
    //

    val hostState = remember { SnackbarHostState() }
    LaunchedEffect(Unit) {
        uiState.snackbarMessage.collect { message ->
            if (message != null) {
                hostState.showSnackbar(message)
            }
        }
    }


    //Scaffold used top level so that for any screen snack-bar can be used
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = hostState)
        },
        topBar = {
            if (isCompact) {
                TopAppBar(
                    title = { Text(text = "Login Screen") },
                    navigationIcon = {
                        IconButton(onClick = uiState::onOpenDrawerRequest) {
                            Icon(Icons.Filled.Menu, null)
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(8.dp)
                    )
                )
            }
        }

    ) { scaffoldPadding ->
        Box(Modifier.fillMaxSize()) {
            //box is taken to tenderize the progress bar
            if (isCompact) {
                CompactScreen(
                    modifier = Modifier.padding(scaffoldPadding),
                    headerSection = headerSection,
                    loginSection = loginSection,
                    destination = HomeDrawerItems.drawerGroups,
                    drawerState = uiState.drawerState,
                    onDrawerItemClick = { uiState.onCloseDrawerRequest() },
                    onDrawerCloseRequested = uiState::onCloseDrawerRequest
                )
            } else if (isMedium) {
                MediumScreen(
                    modifier = Modifier.padding(scaffoldPadding),
                    headerSection = headerSection,
                    loginSection = loginSection,
                    navigationSection = navigationSection
                )
            } else if (isExpanded) {
                ExpandedScreen(
                    modifier = Modifier.padding(scaffoldPadding),
                    headerSection = headerSection,
                    loginSection = loginSection,
                    navigationSection = navigationSection
                )
            }
            if (uiState.showProgressBar.collectAsState().value) {
                IndeterminateCircularIndicator(
                    Modifier.align(Alignment.Center)
                        .size(64.dp)
                )
            }
        }
    }


}


