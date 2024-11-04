package just.digitaldiary

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import just.digitaldiary.theme.AppTheme
import navigation.RootNavHost


class MainActivity : ComponentActivity() {
    private lateinit var eventHandler: AppEventHandler
    private var isTokenReady = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        eventHandler = AppEventHandler(this)
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition { !isTokenReady }



        setContent {
            val tokenViewModel: TokenViewModel = viewModel()
            val token by tokenViewModel.token.collectAsState()
            val isTokenLoaded by tokenViewModel.isTokenLoaded.collectAsState()

            // Update isTokenReady once the token loading is complete
            LaunchedEffect(isTokenLoaded) {
                if (isTokenLoaded) {
                    isTokenReady = true
                }

            }
            AppTheme {
                RootNavHost(
                    token = token,
                    onEvent = eventHandler::handleEvent,
                    onTokenSaveRequest = tokenViewModel::saveToken,
                    onTokenDeleteRequest = tokenViewModel::clearToken
                )

            }
        }
    }



}
