import administration.navgraph.AdminOfficeAndSubOfficeRoute
import administration.navgraph.AdminOfficersScreen
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import auth.ui.AuthNavHostDesktop
import common.newui.CustomSnackBarData
import faculty.route.FacultiesScreen


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
            var data by remember {
                mutableStateOf<CustomSnackBarData?>(
                    CustomSnackBarData(
                        message = "Failed", details = "No Internet connect", isError = true
                    )
                )
            }
            MaterialTheme {
//                SnackBarDecoratorNew(
//                    snackBarData = data,
//                    onDetailsClosed = {
//                        data = null
//                    }
//                ) {
//
//                }
//                NavigationRoot()
//              AuthNavHostDesktop()
//                FacultiesScreen(
//                    onTeacherListRequest = {},
//                    onExitRequest = {},
//                    backHandler = {}
//                )
                AdminOfficeAndSubOfficeRoute({})

            }
        }
    }

}

