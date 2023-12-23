package com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.login

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.LocationSearching
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Note
import androidx.compose.material.icons.filled.Person4
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


val homeDestinations = listOf(
    NavigationItem(
        label = "Menu",
        unFocusedIcon = Icons.Default.Menu
    ),
    NavigationItem(
        label = "Factually Members",
        unFocusedIcon = Icons.Default.Person4
    ),
    NavigationItem(
        label = "Admin Office",
        unFocusedIcon = Icons.Default.Settings
    ),
    NavigationItem(
        label = "Vehicle Tracking",
        unFocusedIcon = Icons.Default.LocationSearching
    ),
    NavigationItem(
        label = "Daily Diary",
        unFocusedIcon = Icons.Default.Note
    ),
    NavigationItem(
        label = "Daily Diary",
        unFocusedIcon = Icons.Default.CalendarMonth
    ),
    NavigationItem(
        label = "Explore JUST",
        unFocusedIcon = Icons.Default.Explore
    )
)


/*
ViewModel   should be independent of UI Layout or look,
that is why separate factory is made,
 */

class HomeViewModel(
    private val scope: CoroutineScope,
) {
    private val _snackbarMessage = MutableStateFlow<String?>("Welcome")
    val snackbarMessage = _snackbarMessage.asStateFlow()
    private val _showProgressBar = MutableStateFlow(false)
    val showProgressBar = _showProgressBar.asStateFlow()
    fun tryLogin() {
        scope.launch {
            _showProgressBar.value = true
            delay(2_000)
            _snackbarMessage.value = "Not Implemented Yet..."
            _showProgressBar.value = false
        }

    }

}

val medium = WindowWidthSizeClass.Medium
val expanded = WindowWidthSizeClass.Expanded
val compact = WindowWidthSizeClass.Compact

@Composable
fun LoginScreen() {
    val scope = rememberCoroutineScope()
    val uiState = remember {
        HomeViewModel(scope)
    }
    val factory = LoginLayoutFactoryState(scope, homeDestinations, { })

    LoginLayoutFactory(
        factory = factory,
        destination = homeDestinations,
        snackbarMessage = null,
        headerSection = { LoginSectionHeader() },
        loginSection = {
            LoginFieldsNControls(
                modifier = Modifier,
                isHorizontal = true,
                onRegisterRequest = uiState::tryLogin,
                onLoginRequest = uiState::tryLogin,
                onPasswordResetRequest = uiState::tryLogin
            )
        },
    )

}


