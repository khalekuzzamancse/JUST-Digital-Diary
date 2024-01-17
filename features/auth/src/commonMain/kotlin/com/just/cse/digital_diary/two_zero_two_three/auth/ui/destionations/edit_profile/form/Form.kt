package com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.edit_profile.form

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.common_ui.WindowSizeDecorator

@Composable
fun EditForm(
    modifier: Modifier = Modifier,
    fieldModifier: Modifier,
    data: EditFormData,
    onNameChanged: (String) -> Unit,
    onUserNameChanged: (String) -> Unit,
    onDeptChanged: (String) -> Unit,
) {
    WindowSizeDecorator(
        onCompact = {
            CompactModeEditForm(
                modifier = modifier,
                fieldModifier = fieldModifier,
                onNameChanged = onNameChanged,
                onUserNameChanged = onUserNameChanged,
                onDeptChanged = onDeptChanged,
                data = data
            )
        },
        onNonCompact = {
            NonCompactModeEditForm(
                modifier = modifier,
                fieldModifier = fieldModifier,
                onNameChanged = onNameChanged,
                onUserNameChanged = onUserNameChanged,
                onDeptChanged = onDeptChanged,
                data = data
            )
        }
    )
}