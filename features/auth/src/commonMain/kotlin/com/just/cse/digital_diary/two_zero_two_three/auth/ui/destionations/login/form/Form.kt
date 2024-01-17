package com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.login.form

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.common_ui.WindowSizeDecorator

@Composable
internal fun LoginForm(
    fieldModifier: Modifier = Modifier,
    formModifier: Modifier = Modifier,
    data: LoginFormData,
    onUserNameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
) {
    WindowSizeDecorator(
        onCompact = {
            CompactScreenLoginForm(
                data = data,
                fieldModifier = fieldModifier,
                formModifier = formModifier,
                onUserNameChanged = onUserNameChanged,
                onPasswordChanged = onPasswordChanged,
            )
        },
        onNonCompact = {
            NonCompactScreenLoginForm(
                data = data,
                fieldModifier = Modifier.fillMaxWidth(),
                formModifier = formModifier,
                onUserNameChanged = onUserNameChanged,
                onPasswordChanged = onPasswordChanged,
            )
        }
    )

}