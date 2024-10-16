package common.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun SnackNProgressBarDecorator(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    snackBarMessage: String?,
    navigationIcon: (@Composable () -> Unit)? = null,
    fab: @Composable () -> Unit = {},
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
        },
        floatingActionButton = fab
    ) { innerPadding ->
        Box(Modifier.padding(innerPadding).fillMaxSize()) {
            content()
            if (isLoading)
                _LoadingUi(
                    Modifier.matchParentSize().align(Alignment.Center)
                        .background(Color.Gray.copy(alpha = 0.7f))
                )
        }

    }

}

@Composable
private fun _LoadingUi(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(64.dp)
        )
    }

}