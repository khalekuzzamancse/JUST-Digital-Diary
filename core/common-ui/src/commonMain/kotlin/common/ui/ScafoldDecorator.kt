package common.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * @param message snackBar message
 * @param isLoading
 */
@Composable
fun SnackNProgressBarDecorator(
    modifier: Modifier = Modifier,
    isLoading: Boolean=false,
    message: String?=null,
    navigationIcon: (@Composable () -> Unit)? = null,
    fab: @Composable () -> Unit = {},
    content: @Composable BoxScope.() -> Unit={},
) {
    SnackNProgressBarDecorator(
        modifier = modifier,
        isLoading = isLoading,
        message = if (message != null)
            SnackBarMessage.neutral(message) else null,
        navigationIcon = navigationIcon,
        fab = fab,
        content = content
    )

}
/**
 * @param message snackBar message
 * @param isLoading
 */


@Composable
fun SnackNProgressBarDecorator(
    modifier: Modifier = Modifier,
    isLoading: Boolean=false,
    message: SnackBarMessage?=null,
    navigationIcon: (@Composable () -> Unit)? = null,
    fab: @Composable () -> Unit = {},
    content: @Composable BoxScope.() -> Unit,
) {
    val hostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    // Track the previous snack bar coroutine
    var previousJob: Job? by remember { mutableStateOf(null) }

    if (message != null) {
        LaunchedEffect(message) {
            // Cancel the previous snack bar if a new message arrives
            previousJob?.cancel()

            // Launch a new coroutine for the latest message
            previousJob = scope.launch {
                hostState.showSnackbar(message = message.text)
            }
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
        Box(
            Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            content()

            if (isLoading)
                _LoadingUi(
                    Modifier
                        .matchParentSize()
                        .align(Alignment.Center)
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