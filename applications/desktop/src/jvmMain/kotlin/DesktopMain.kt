import administration.navgraph.AdminOfficeAndSubOfficeRoute
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import faculty.route.FacultyNDepartments
import faculty.ui.faculty.facultylist.components.FacultyCard
import miscellaneous.ui.home.home.HomeDestination
import navigation.themes.AppTheme
import notebook.ui.notedetails.Note
import notebook.ui.notelist.NoteCard


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
                    //  HomeDestination({})
//                    FacultyNDepartments(onExitRequest = {}, backHandler = {})
//                    AdminOfficeAndSubOfficeRoute({})
//
//
//                }
                    val note = Note(
                        id = "1",
                        title = "Sample Note",
                        description = "This is a sample description for a note.",
                        timeStamp = "2023-04-05T14:30:00Z" // ISO 8601 format
                    )
                    NoteCard(
                        onClick = {},
                        note = note
                    )
                }

                }
            }
        }

    }

