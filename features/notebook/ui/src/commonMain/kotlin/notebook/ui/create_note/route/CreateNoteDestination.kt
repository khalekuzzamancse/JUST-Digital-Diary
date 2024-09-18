package notebook.ui.create_note.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import common.newui.SnackNProgressBarDecorator
import common.ui.progressbar.ProgressBarNSnackBarDecorator
import notebook.ui.create_note.component.CreateNote

/**
 * * It used to create  the Notes
 * * It is it is internal to the module can outer module can not directly access it.
 * * It this can handle both compact,medium,expanded window size.
 *    be called to exit the from note creation screen
 */
@Composable
fun CreateNoteDestination(
    modifier: Modifier=Modifier,
    viewModel: CreateNoteViewModel,
) {
    SnackNProgressBarDecorator(
        snackBarData = viewModel.snackBarMessage.collectAsState().value,
        showProgressBar = viewModel.showProgressBar.collectAsState().value,
        onSnackBarCloseRequest = viewModel::clearSnackBarMessage
    ) {
        CreateNote(
            modifier = modifier,
            data = viewModel.data.collectAsState().value,
            onTitleChanged = viewModel::onTitleChanged,
            onDescriptionChanged = viewModel::onDescriptionChanged,
            onCreate = viewModel::onCreateRequest,
            onExitRequest = {}
        )
    }

}