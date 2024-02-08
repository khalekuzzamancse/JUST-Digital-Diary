import androidx.compose.material3.Text
import androidx.compose.ui.window.Window
import org.jetbrains.skiko.wasm.onWasmReady

fun main() {
    onWasmReady {
        Window ("Web App"){
            Text("This is Web App")
        }
    }
}