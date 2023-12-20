import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.just.cse.digitaldiary.twozerotwothree.CommonMainGreeting

fun main() {
    application {
        Window(
            title = "Compose Desktop",
            onCloseRequest = ::exitApplication
        ){
            CommonMainGreeting("Desktop")
        }
    }

}