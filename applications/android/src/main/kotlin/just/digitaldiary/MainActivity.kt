package just.digitaldiary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import just.digitaldiary.theme.AppTheme
import kotlinx.coroutines.CoroutineScope
import navigation.AppEvent
import navigation.RootNavHost

// MainActivity.kt

class MainActivity : ComponentActivity() {
    private lateinit var eventHandler: AppEventHandler
    private var isTokenReady = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        initializeDependencies()
        initializeSplashScreen()

        setContent { BuildContent() }
    }

    private fun initializeDependencies() {
        eventHandler = AppEventHandler(this)
    }

    private fun initializeSplashScreen() {
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition { !isTokenReady }
    }

    @Composable
    private fun BuildContent() {
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
            val scope = rememberCoroutineScope()
            var showDialog by rememberSaveable { mutableStateOf(false) }
            var contactString by rememberSaveable { mutableStateOf("") }
            var currentEvent by rememberSaveable { mutableStateOf<AppEvent?>(null) }

            RootNavHost(
                token = token,
                onEvent = { event ->
                    handleEvent(
                        event,
                        scope,
                        tokenViewModel,
                        { showDialog = it },
                        { contactString = it },
                        { currentEvent = it }
                    )
                }
            )

            if (showDialog) {
                ContactSelectionDialog(
                    contactString = contactString,
                    onDismissRequest = { showDialog = false },
                    onItemSelected = { selectedItem ->
                        handleContactSelection(selectedItem, currentEvent)
                        showDialog = false
                    }
                )
            }
        }
    }

    private fun handleEvent(
        event: AppEvent,
        scope: CoroutineScope,
        tokenViewModel: TokenViewModel,
        setShowDialog: (Boolean) -> Unit,
        setContactString: (String) -> Unit,
        setCurrentEvent: (AppEvent?) -> Unit
    ) {
        when (event) {
            is AppEvent.CallRequest -> {
                setContactString(event.number)
                setCurrentEvent(event)
                setShowDialog(true)
            }
            is AppEvent.MessageRequest -> {
                setContactString(event.number)
                setCurrentEvent(event)
                setShowDialog(true)
            }
            is AppEvent.EmailRequest -> {
                setContactString(event.email)
                setCurrentEvent(event)
                setShowDialog(true)
            }
            is AppEvent.LoginSuccess -> {
                tokenViewModel.saveToken(event.token)
            }
            is AppEvent.LogOut -> {
                tokenViewModel.clearToken()
            }
            else -> {
                eventHandler.handleEvent(event)
            }
        }
    }

    private fun handleContactSelection(selectedItem: String, currentEvent: AppEvent?) {
        currentEvent?.let { event ->
            when (event) {
                is AppEvent.CallRequest -> {
                    eventHandler.handleEvent(event.copy(number = selectedItem))
                }
                is AppEvent.MessageRequest -> {
                    eventHandler.handleEvent(event.copy(number = selectedItem))
                }
                is AppEvent.EmailRequest -> {
                    eventHandler.handleEvent(event.copy(email = selectedItem))
                }
                else -> {}
            }
        }
    }
}
