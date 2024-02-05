import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import com.just.cse.digital_diary.two_zero_two_three.root_home.AppEvent
import com.just.cse.digital_diary.two_zero_two_three.root_home.NavigationRoot
import com.just.cse.digital_diary.two_zero_two_three.root_home.RootModule


fun main() {
    application {
        val state= remember { WindowState(
            position = WindowPosition(0.dp, 0.dp),
        ) }
        state.size= DpSize(width = 400.dp, height =700.dp)
        Window(
            state=state,
            title = "JUST Digital Diary",
            onCloseRequest = ::exitApplication
        ) {
            MaterialTheme {
                NavigationRoot()
            }
        }
    }

}

