import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import calendar.ui.factory.UIFactory
import calendar.ui.viewer.AcademicCalenderViewer
import navigation.themes.AppTheme


fun main() {
    application {
        val state = remember {
            WindowState(
                position = WindowPosition(0.dp, 0.dp),
            )
        }
        state.size = DpSize(width = 400.dp, height = 700.dp)
        Window(
            state = state,
            title = "JUST Digital Diary",
            onCloseRequest = ::exitApplication
        ) {

            MaterialTheme {
//                NavigationRoot()
//              AuthNavHostDesktop()
                AppTheme {
//                    HolidayEditorUI(
//                        onSnackBarMsgRequest = {},
//                        controller = remember { UIFactory.createCalenderEditorController() }
//                    )
                    AcademicCalenderViewer(
                        controller = remember { UIFactory.createCalenderController() },
                        onSnackBarMsgRequest = {},
                    )
//                    HolidayEditorUI(
//                        onSnackBarMsgRequest = {},
//                        controller = remember { UIFactory.createCalenderEditorController() }
//                    )

                }

            }
        }
    }

}

