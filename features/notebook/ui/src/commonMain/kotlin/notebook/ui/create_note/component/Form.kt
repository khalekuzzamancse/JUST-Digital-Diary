package notebook.ui.create_note.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import common.ui.WindowSizeDecorator


@Composable
internal fun CreateNoteForm (
    modifier: Modifier = Modifier,
    data: CreateNoteData,
    onTitleChanged:(String)->Unit,
    onDescriptionChanged:(String)->Unit
) {
    WindowSizeDecorator(
        onCompact = {
            CompactModeCreateNoteForm(
                modifier=modifier,
                data=data,
                onTitleChanged=onTitleChanged,
                onDescriptionChanged=onDescriptionChanged
            )
        }
        , onNonCompact = {
            NonCompactModeCreateNoteForm(
                modifier=modifier,
                data=data,
                onTitleChanged=onTitleChanged,
                onDescriptionChanged=onDescriptionChanged
            )
        }
    )

}