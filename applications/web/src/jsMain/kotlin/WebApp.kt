import androidx.compose.material3.Text
import androidx.compose.ui.input.key.Key.Companion.Window
import org.jetbrains.skiko.wasm.onWasmReady
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document


@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {
        App()
    }
}
