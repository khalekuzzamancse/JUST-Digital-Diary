package notebook.ui.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import notebook.ui.create_note.route.CreateNoteDestination
import notebook.ui.create_note.route.CreateNoteViewModel
import notebook.ui.route.NotesAndDetailsRoute
import notebook.ui.route.NotesListViewModel


object NotesFeatureNavGraph {
    private const val NOTE_CREATION_SCREEN = "NoteCreation"
    private const val NOTE_AND_DETAILS_ROUTE = "NoteListScreen"

    @Composable
    fun Graph(
        navController: NavHostController = rememberNavController(),
        onBackPressed:()->Unit,
        onExitRequest:()->Unit,
    ) {
       val viewModel= remember { NotesListViewModel() }
        NavHost(
            navController = navController,
            startDestination = NOTE_AND_DETAILS_ROUTE
        ) {
            composable(NOTE_AND_DETAILS_ROUTE) {
                NoteListTopBarDecorator(
                    onNoteCreateRequest = {
                        navController.navigate(NOTE_CREATION_SCREEN)
                    }
                ) { padding ->
                    NotesAndDetailsRoute(
                        modifier = Modifier.padding(padding),
                        viewModel=viewModel,
                        onExitRequest = onExitRequest
                    ) { callback ->
                        BackHandler(onBack = {
                            val isConsumed = callback()
                            if (!isConsumed) {
                                onBackPressed()
                            }
                        })
                    }
                }
            }
            composable(NOTE_CREATION_SCREEN) {
                val vm= remember { CreateNoteViewModel() }
                CreateNoteDestination(Modifier,vm)
            }
        }


    }
}