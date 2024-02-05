package com.just.cse.digital_diary.two_zero_two_three.root_home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import com.just.cse.digital_diary.two_zero_two_three.auth.destination.AuthModuleEntryPoint
import com.just.cse.digital_diary.two_zero_two_three.root_home.modal_drawer.RootModuleDrawer
import com.just.cse.digital_diary.two_zero_two_three.root_home.navgraph.screens.ModalDrawerHandler
import com.just.cse.digital_diary.two_zero_two_three.root_home.navgraph.screens.TopMostDestinations
import com.just.cse.digitaldiary.twozerotwothree.core.di.auth.AuthComponentProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
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
            val isSuccess = AuthComponentProvider.saveSignInInfo(username, password)
            if (isSuccess) {
                println("Saved Successfully")
            } else {
                println("Saved Failure")
            }
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
            RootModuleDrawer(
                drawerHandler = drawerHandler,
                navigationEvent = NavigationEvent(),
                onLogOutRequest = ::onLogout
            )
            {
                TopMostDestinations.Home(
                    onCreateNoteRequest = {},
                    onOpenDrawerRequest = drawerHandler::openDrawer
                ) {
                }
            }
        }


    }

}