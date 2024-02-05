package com.just.cse.digital_diary.two_zero_two_three.root_home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import com.just.cse.digital_diary.two_zero_two_three.auth.destination.AuthModuleEntryPoint
import com.just.cse.digital_diary.two_zero_two_three.common_ui.top_bar.SimpleTopBar
import com.just.cse.digital_diary.two_zero_two_three.root_home.modal_drawer.RootModuleDrawer
import com.just.cse.digital_diary.two_zero_two_three.root_home.navgraph.screens.ModalDrawerHandler
import com.just.cse.digital_diary.two_zero_two_three.root_home.navgraph.screens.TopMostDestinations
import com.just.cse.digitaldiary.twozerotwothree.core.di.auth.AuthComponentProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun NavigationRoot() {
    Navigator(DrawerHost)
}


internal object DrawerHost : Screen {
    private val drawerHandler = ModalDrawerHandler()
    private var signedIn = AuthComponentProvider.isSignIn()
    private fun onLoginSuccess(username: String, password: String) {
        CoroutineScope(Dispatchers.Default).launch {
            AuthComponentProvider.saveSignInInfo(username, password)
        }
    }

    private fun onLogout() {

        AuthComponentProvider.signInOut()

    }

    @Composable
    override fun Content() {
        if (!signedIn.collectAsState(true).value) {
            AuthModuleEntryPoint(
                onLoginSuccess = ::onLoginSuccess
            )
        } else {
            Scaffold(
                topBar = {
                    SimpleTopBar(
                        title = "Home",
                        navigationIcon = Icons.Default.Menu,
                        onNavigationIconClick = drawerHandler::openDrawer
                    )
                }
            ) {
                RootModuleDrawer(
                    modifier = Modifier.padding(it),
                    drawerHandler = drawerHandler,
                    navigationEvent = NavigationEvent(),
                    onLogOutRequest = ::onLogout
                )
                {destination->
                    NavigationGraph.NavHost(destination)
                }

            }

        }


    }

}
