import androidx.compose.material.MaterialTheme
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.just.cse.digital_diary.two_zero_two_three.root_home.RootModule


fun main() {
    application {
        Window(
            title = "Compose Desktop",
            onCloseRequest = ::exitApplication
        ) {
            MaterialTheme {
                RootModule()
            }
        }
    }

}

