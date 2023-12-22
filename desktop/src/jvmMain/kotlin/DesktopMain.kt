import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.login.Demo
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.theme.AuthModuleTheme

fun main() {
    application {
        Window(
            title = "Compose Desktop",
            onCloseRequest = ::exitApplication
        ){
            AuthModuleTheme {
                Demo()
              //LoginSection()
            }

        }
    }

}

@Composable
fun AuthModule() {

}
