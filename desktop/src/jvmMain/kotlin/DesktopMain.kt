import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.just.cse.digital_diary.two_zero_two_three.auth.AuthModuleGreeting
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.AuthTextFieldPreview
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.form.FormTextInputPreview
import com.just.cse.digitaldiary.twozerotwothree.CommonMainGreeting

fun main() {
    application {
        Window(
            title = "Compose Desktop",
            onCloseRequest = ::exitApplication
        ){
            AuthTextFieldPreview()
        }
    }

}