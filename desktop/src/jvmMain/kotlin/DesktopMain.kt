import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.faculty.FacultyLayoutBuilder2Demo
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.faculty.common.FacultyLayoutFactoryDemo
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.theme.AuthModuleTheme

fun main() {
    application {
        Window(
            title = "Compose Desktop",
            onCloseRequest = ::exitApplication
        ){
            AuthModuleTheme {
              // LoginScreen()
                //FacultyLayoutOnExpanded()
                //FacultyLayoutFactoryDemo()
                FacultyLayoutBuilder2Demo()
            }

        }
    }

}

@Composable
fun AuthModule() {

}
