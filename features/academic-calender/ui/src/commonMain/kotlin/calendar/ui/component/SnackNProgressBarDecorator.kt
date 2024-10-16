package calendar.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch

@Composable
fun SnackNProgressBarDecorator(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    snackBarMessage: String?,
    navigationIcon: (@Composable () -> Unit)? = null,
    content: @Composable BoxScope.() -> Unit,
) {
    val hostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    if (snackBarMessage != null) {
        scope.launch {
            hostState.showSnackbar(message = snackBarMessage)
        }
    }

    Scaffold(
        modifier = modifier,
        snackbarHost = {
            SnackbarHost(hostState = hostState)
        },
        topBar = {
            if (navigationIcon != null) {
                navigationIcon()
            }
        }
    ) { innerPadding ->
        Box(Modifier.padding(innerPadding).fillMaxSize()) {
            content()
            if (isLoading)
                LoadingUi(Modifier.align(Alignment.Center))
        }

    }

}