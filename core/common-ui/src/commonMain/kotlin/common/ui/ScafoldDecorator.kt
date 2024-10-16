package common.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
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

/**
 * @param snackBarMessage snackBar message
 * @param isLoading
 */
@Composable
fun SnackNProgressBarDecorator(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    snackBarMessage: String?,
    navigationIcon: (@Composable () -> Unit)? = null,
    fab: @Composable () -> Unit = {},
    content: @Composable BoxScope.() -> Unit,
) {
    SnackNProgressBarDecorator(
        modifier = modifier,
        showProgressBar = isLoading,
        message = if (snackBarMessage != null)
            SnackBarMessage.neutral(snackBarMessage) else null,
        navigationIcon = navigationIcon,
        fab = fab,
        content = content
    )

}
/**
 * @param message snackBar message
 * @param showProgressBar
 */
@Composable
fun SnackNProgressBarDecorator(
    modifier: Modifier = Modifier,
    showProgressBar: Boolean,
    message: SnackBarMessage?,
    navigationIcon: (@Composable () -> Unit)? = null,
    fab: @Composable () -> Unit = {},
    content: @Composable BoxScope.() -> Unit,
) {
    val hostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    if (message != null) {
        scope.launch {
            hostState.showSnackbar(message = message.text)
        }
    }

    Scaffold(
        modifier = modifier,
        snackbarHost = {
            if (message != null) {
                CustomSnackBarHost(
                    state = hostState,
                    message = message
                )
            }
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
            if (showProgressBar)
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

//TODO:
/**
 * A class representing a message with different types: Error, Success, and Neutral.
 *
 * This class is used to encapsulate message data and display it in components such as SnackBars.
 *
 * **Note:** Do not use the constructor directly. Use the provided factory methods Error, Success, and Neutral
 * to create instances of this class
 * - Tested works well for both dark and white mode, however don't forget to the check
 * to make sure it works fine with your theme
 * Example usage:
 * ```
 * CustomSnackBarHost(
 * state=....,
 * message = Message.Error("Something went wrong!"
 * )
 * ```
 */
@Composable
fun CustomSnackBarHost(
    state: SnackbarHostState,
    message: SnackBarMessage
) {
    SnackbarHost(
        hostState = state,
        snackbar = { data ->
            val isDarkTheme = isSystemInDarkTheme()

            val textColor = when (message.type) {
                MessageType.Success -> if (!isDarkTheme) Color(0xFF008A4B) else Color(0xFF80E1A1) // Adjust green for dark mode
                MessageType.Failure -> if (!isDarkTheme) Color(0xFFE12F2B) else Color(0xFFFF6B6B) // Adjust red for dark mode
                MessageType.Neutral -> MaterialTheme.colorScheme.onSurface // Automatically pick based on theme
            }

            Snackbar(
                snackbarData = data,
                containerColor = MaterialTheme.colorScheme.surface, // Background that adapts to the theme
                contentColor = textColor,
            )

        }
    )
}

class SnackBarMessage private constructor(val text: String, val type: MessageType) {

    companion object {
        fun error(text: String): SnackBarMessage {
            return SnackBarMessage(text, MessageType.Failure)
        }

        fun success(text: String): SnackBarMessage {
            return SnackBarMessage(text, MessageType.Success)
        }

        fun neutral(text: String): SnackBarMessage {
            return SnackBarMessage(text, MessageType.Neutral)
        }
    }
}

enum class MessageType {
    Success,
    Failure,
    Neutral
}