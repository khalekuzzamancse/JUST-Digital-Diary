package com.just.cse.digital_diary.two_zero_two_three.root_home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.just.cse.digital_diary.features.faculty.destination.TeacherListScreen
import com.just.cse.digital_diary.two_zero_two_three.auth.destination.AuthModuleEntryPoint
import com.just.cse.digital_diary.two_zero_two_three.common_ui.top_bar.SimpleTopBar
import com.just.cse.digital_diary.two_zero_two_three.root_home.modal_drawer.RootModuleDrawer
import com.just.cse.digital_diary.two_zero_two_three.root_home.navgraph.screens.ModalDrawerHandler
import com.just.cse.digitaldiary.twozerotwothree.core.di.auth.AuthComponentProvider
import kotlinx.coroutines.launch

@Composable
fun NavigationRoot() {
    val signedIn = AuthComponentProvider.observeSignIn()
    val signOut: () -> Unit = {
        AuthComponentProvider.signInOut()
    }
    if (!signedIn.collectAsState(true).value){
        AuthOption()
    }
    else {
        SignedInUserContent(signOut)
    }

}
@Composable
fun SignedInUserContent(
    signOut:()->Unit,
){

    val drawerHandler = remember { ModalDrawerHandler() }
    Navigator(DrawerHost) { navigator ->
        Scaffold(
            topBar = {
                SimpleTopBar(
                    title = "Home",
                    navigationIcon = Icons.Default.Menu,
                    onNavigationIconClick = drawerHandler::openDrawer
                )
            }
        ) { paddingValue ->
            SlideTransition(
                navigator = navigator,
                modifier = Modifier.padding(paddingValue)
            ) {
                RootModuleDrawer(
                    modifier = Modifier,
                    drawerHandler = drawerHandler,
                    navigationEvent = NavigationEvent(),
                    onLogOutRequest = signOut
                ) { destination ->
                    NavigationGraph.NavHost(destination)
                }

            }

        }
    }
}

@Composable
fun AuthOption(
) {
    val scope = rememberCoroutineScope()
    val onLoginSuccess: (username: String, password: String) -> Unit = { username, password ->
        scope.launch {
            AuthComponentProvider.saveSignInInfo(username, password)
        }
    }
    AuthModuleEntryPoint(
        onLoginSuccess = onLoginSuccess
    )


}

/**
 * * Empty screen just for start the navigation

 */
internal object DrawerHost : Screen {

    @Composable
    override fun Content() {


    }

}

class TeacherList(
    private val id: String,
    private val onExitRequest: () -> Unit
) : Screen {
    @Composable
    override fun Content() {
        TeacherListScreen(
            deptId = id,
            onExitRequest = onExitRequest
        )
    }

}