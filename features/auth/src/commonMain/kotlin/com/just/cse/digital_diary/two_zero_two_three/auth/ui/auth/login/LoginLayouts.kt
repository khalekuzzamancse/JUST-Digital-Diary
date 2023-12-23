package com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.login

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.modal_drawer.ModalDrawerGroup
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.modal_drawer.ScreenWithDrawer
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun LoginLayoutNonCompactScreen(
    headerSection: @Composable ((Modifier) -> Unit),
    loginSection: @Composable ((Modifier) -> Unit)
) {
    val screenWidthType = calculateWindowSizeClass().widthSizeClass
    val medium = WindowWidthSizeClass.Medium
    val expanded = WindowWidthSizeClass.Expanded
    when (screenWidthType) {
        medium, expanded -> {
            if (screenWidthType == medium) {
                LoginLayoutOnMediumScreen(
                    modifier = Modifier,
                    headerSection = headerSection,
                    loginSection = loginSection,
                )
            } else {
                LoginLayoutOnExpandedScreen(
                    modifier = Modifier,
                    headerSection = headerSection,
                    loginSection = loginSection
                )
            }
        }
    }
}

@Composable
fun LoginLayoutOnMediumScreen(
    modifier: Modifier,
    headerSection: @Composable ((Modifier) -> Unit),
    loginSection: @Composable ((Modifier) -> Unit)
) {
    Row(modifier = modifier.padding(16.dp).fillMaxSize()) {
        Column {
            headerSection(Modifier)
            loginSection(Modifier.wrapContentWidth())
        }
    }
}

@Composable
fun LoginLayoutOnExpandedScreen(
    modifier: Modifier,
    headerSection: @Composable ((Modifier) -> Unit),
    loginSection: @Composable ((Modifier) -> Unit)
) {
    Row(modifier = modifier.padding(16.dp).fillMaxSize()) {
        loginSection(Modifier.weight(1f).wrapContentWidth())
        headerSection(Modifier)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
//Complete stateless composable
@Composable
fun LoginLayoutOnCompactScreen(
    modifier: Modifier,
    drawerState: DrawerState,
    onDrawerItemClick: (String) -> Unit,
    onDrawerCloseRequested: () -> Unit,
    onDrawerOpenRequest: () -> Unit,
    destination: List<ModalDrawerGroup>,
    snackbarMessage:String?=null,
    headerSection: @Composable ((Modifier) -> Unit),
    loginSection: @Composable ((Modifier) -> Unit)
) {
    val hostState = remember { SnackbarHostState() }
    val scope= rememberCoroutineScope()
    snackbarMessage?.let {message->
        scope.launch {
            hostState.showSnackbar(message)
        }
    }
    BoxWithConstraints(modifier) {
        val width = this.maxWidth
        Row(modifier = Modifier.fillMaxSize()) {
            ScreenWithDrawer(
                drawerState=drawerState,
                destination = destination,
                closeDrawer = onDrawerCloseRequested,
                onDrawerItemClick = onDrawerItemClick,
            ) {
                //Scaffold used top level so that for any screen snack-bar can be used
                Scaffold(
                    modifier = modifier,
                    snackbarHost = {
                        SnackbarHost(hostState = hostState)
                    },
                    topBar = {
                        TopAppBar(
                            title = { Text(text = "Login Screen") },
                            navigationIcon = {
                                IconButton(onClick = onDrawerOpenRequest) {
                                    Icon(Icons.Filled.Menu, null)
                                }
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(8.dp)
                            )
                        )

                    }
                ) { scaffoldPadding ->
                    Column(Modifier.padding(scaffoldPadding)) {
                        headerSection(Modifier)
                        loginSection(Modifier)
                    }
                }

            }
        }

    }
}