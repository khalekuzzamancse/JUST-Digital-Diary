package com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.edit_profile.form

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Person4
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.common.AuthDropDownMenu
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.common.AuthTextField
import com.just.cse.digital_diary.two_zero_two_three.common_ui.form_layout.FormLayout


@Composable
fun NonCompactModeEditForm(
    modifier: Modifier = Modifier,
    fieldModifier: Modifier,
    data: EditFormData,
    onNameChanged: (String) -> Unit,
    onUserNameChanged: (String) -> Unit,
    onDeptChanged: (String) -> Unit,
) {

    NonCompactModeEditForm(
        modifier = modifier,
        fieldModifier = fieldModifier,
        name = data.name,
        onNameChanged = onNameChanged,
        username = data.username,
        onUserNameChanged = onUserNameChanged,
        departments = listOf("CSE", "EEE"),
        dept = data.dept,
        onDeptChanged = onDeptChanged,
    )

}

@Composable
fun NonCompactModeEditForm(
    modifier: Modifier = Modifier,
    fieldModifier: Modifier,
    name: String,
    onNameChanged: (String) -> Unit,
    username: String,
    onUserNameChanged: (String) -> Unit,
    departments: List<String>,
    dept: String,
    onDeptChanged: (String) -> Unit,
) {
    FormLayout(
        eachRow1stChildMaxWidth = 200.dp,
        verticalGap = 8.dp,
        horizontalGap = 4.dp,
        modifier = modifier,
    ) {
        Text(text = EditFormLabels.FULL_NAME)
        AuthTextField(
            modifier = fieldModifier,
            value = name,
            onValueChanged = onNameChanged,
            keyboardType = KeyboardType.Text,
            leadingIcon = Icons.Default.Person,
        )
        Text(text = EditFormLabels.USER_NAME)
        AuthTextField(
            modifier = fieldModifier,
            value = username,
            onValueChanged = onUserNameChanged,
            keyboardType = KeyboardType.Text,
            leadingIcon = Icons.Default.Person4,
        )
        Text(text = EditFormLabels.Dept)
        AuthDropDownMenu(
            modifier = fieldModifier,
            options = departments,
            onOptionSelected = onDeptChanged,
            selected = dept
        )


    }

}





