package com.just.cse.digital_diary.two_zero_two_three.root_home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.just.cse.digital_diary.two_zero_two_three.auth.destination.nav_graph.AuthenticationFeatureNavGraph
import com.just.cse.digital_diary.two_zero_two_three.root_home.modal_drawer.RootModuleDrawer
import com.just.cse.digital_diary.two_zero_two_three.root_home.modal_drawer.ModalDrawerHandler
import com.just.cse.digitaldiary.twozerotwothree.core.di.auth.AuthComponentProvider
import kotlinx.coroutines.launch

@Composable
fun NavigationRoot() {
//    val signedIn = AuthComponentProvider.observeSignIn()
    val signOut: () -> Unit = {
        AuthComponentProvider.signInOut()
    }
//    if (!signedIn.collectAsState(true).value) {
//        AuthOption()
//    } else {
//        SignedInUserContent(signOut)
//    }
    SignedInUserContent(signOut)
}

@Composable
fun SignedInUserContent(
    signOut: () -> Unit,
) {

    val drawerHandler = remember { ModalDrawerHandler() }
    Navigator(DrawerHost) { navigator ->
        SlideTransition(
            navigator = navigator,
            modifier = Modifier
        ) {
            RootModuleDrawer(
                modifier = Modifier,
                drawerHandler = drawerHandler,
                onEvent = {

                },
                onLogOutRequest = signOut
            ) { destination ->
                NavigationGraph.NavHost(
                    destination = destination,
                    onTeacherListRequest = {

                    }
                )
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
    AuthenticationFeatureNavGraph()
//    AuthScreen(
//        onLoginSuccess = onLoginSuccess
//    )


}

/**
 * * Empty screen just for start the navigation

 */
internal object DrawerHost : Screen {
    @Composable
    override fun Content() {}
}