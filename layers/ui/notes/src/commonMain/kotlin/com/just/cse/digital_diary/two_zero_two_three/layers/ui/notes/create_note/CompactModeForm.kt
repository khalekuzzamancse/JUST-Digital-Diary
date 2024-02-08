package com.just.cse.digital_diary.two_zero_two_three.layers.ui.notes.create_note

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.layers.ui.notes.common.DescriptionTextField
import com.just.cse.digital_diary.two_zero_two_three.layers.ui.notes.common.TitleTextField

/*
Title:
Description:
 */
@Composable
internal fun CompactModeCreateNoteForm(
    modifier: Modifier=Modifier,
    data: CreateNoteData,
    onTitleChanged:(String)->Unit,
    onDescriptionChanged:(String)->Unit
) {

    Column (
        modifier=modifier,
    ){
        TitleTextField(
            modifier = Modifier.fillMaxWidth(),
            label = "Title",
            value = data.title,
            onValueChanged = onTitleChanged,
        )
        Spacer(Modifier.height(16.dp))
        DescriptionTextField(
            modifier = Modifier.fillMaxWidth(),
            label = "Description",
            value = data.description,
            leadingIcon = Icons.Default.Description,
            onValueChanged = onDescriptionChanged,
            singleLine = false
        )

    }


}