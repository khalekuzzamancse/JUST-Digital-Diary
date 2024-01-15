package com.just.cse.digital_diary.two_zero_two_three.sharing_document.destination.local_destinations.create_note

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.sharing_document.destination.common.DescriptionTextField
import com.just.cse.digital_diary.two_zero_two_three.sharing_document.destination.common.TitleTextField

/*
Title:
Description:
 */


@Composable
internal fun CreateNoteForm(
    data:CreateNoteData,
    onTitleChanged:(String)->Unit,
    onDescriptionChanged:(String)->Unit
) {

    Column (
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