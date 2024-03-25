package auth.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import auth.ui.route._AuthRoute
import kotlinx.coroutines.launch

@Composable
fun AuthNavHostDesktop(){
    val scope = rememberCoroutineScope()
    _AuthRoute(
        onExitRequest = {},
        onLoginSuccess = { username, password ->
            scope.launch {
                //  AuthComponentProvider.saveSignInInfo(username, password)
            }

        },
        backHandler = { onBackButtonPress ->


        }
    )
}