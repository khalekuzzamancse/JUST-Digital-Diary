package notebook.ui.create_note.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import common.ui.form_layout.FormLayout
import notebook.ui.common.DescriptionTextField
import notebook.ui.common.TitleTextField

/*
Title:
Description:
 */
@Composable
internal fun NonCompactModeCreateNoteForm(
    modifier:Modifier=Modifier,
    data: Note,
    onTitleChanged:(String)->Unit,
    onDescriptionChanged:(String)->Unit
) {

    FormLayout(
        eachRow1stChildMaxWidth = 200.dp,
        verticalGap = 8.dp,
        horizontalGap = 4.dp,
        modifier = modifier,
    ){
        Text(text="Title")
        TitleTextField(
            modifier = Modifier.fillMaxWidth(),
            value = data.title,
            onValueChanged = onTitleChanged,
        )
       Text(text="Description")
        DescriptionTextField(
            modifier = Modifier.fillMaxWidth(),
            value = data.description,
            leadingIcon = Icons.Default.Description,
            onValueChanged = onDescriptionChanged,
            singleLine = false
        )

    }


}