import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import navigation.AppTheme
import navigation.RootNavHost


fun main() {
    application {
        val state = remember {
            WindowState(
                position = WindowPosition(0.dp, 0.dp),
            )
        }
        state.size = DpSize(width = 400.dp, height = 700.dp)
        Window(
            state = state,
            title = "JUST Digital Diary",
            onCloseRequest = ::exitApplication
        ) {
            var token by rememberSaveable { mutableStateOf<String?>(null) }

            MaterialTheme {
                AppTheme {
                    RootNavHost(
                        token = token,
                        onTokenSaveRequest = {
                            println("Token:$it")
                            token = it
                        },
                        onTokenDeleteRequest = {},
                        onEvent = {}
                    )

                }

            }
        }
    }

}

