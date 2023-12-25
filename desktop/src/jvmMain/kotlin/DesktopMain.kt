import androidx.compose.material.MaterialTheme
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import cafe.adriel.voyager.navigator.Navigator
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.navigation.HomeNavHost
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.navigation.NavHost

fun main() {
    application {
        Window(
            title = "Compose Desktop",
            onCloseRequest = ::exitApplication
        ) {
            MaterialTheme {
                NavHost()
            }
        }
    }

}

