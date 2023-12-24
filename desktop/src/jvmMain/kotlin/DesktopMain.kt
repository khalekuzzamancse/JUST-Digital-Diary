import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.faculty.FacultyLayoutBuilder2Demo
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.faculty.MainScreen
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.faculty.home_screen.HomeScreen
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.faculty.home_screen.NoteCreation
import com.khalekuzzamanjustcse.taskmanagement.ui_layer.theme.AuthModuleTheme

fun main() {
    application {
        Window(
            title = "Compose Desktop",
            onCloseRequest = ::exitApplication
        ) {
            // LoginScreen()
            //FacultyLayoutOnExpanded()
            //FacultyLayoutFactoryDemo()
            // Home()
            AuthModuleTheme{

              //  FacultyLayoutBuilder2Demo()
            Navigator(MainScreen())
            }





        }
    }

}

