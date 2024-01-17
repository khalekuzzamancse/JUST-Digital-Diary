package com.just.cse.digital_diary.two_zero_two_three.sharing_document.destination.local_destinations.create_note

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.common_ui.WindowSizeDecorator

@Composable
internal fun CreateNoteForm (
    modifier: Modifier = Modifier,
    data:CreateNoteData,
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