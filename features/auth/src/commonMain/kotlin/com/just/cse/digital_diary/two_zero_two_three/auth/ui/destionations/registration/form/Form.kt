package com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.registration.form

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.WindowSizeDecorator

@Composable
internal fun RegistrationForm(
    modifier: Modifier = Modifier,
    fieldModifier: Modifier,
    data: RegistrationFormData,
    onNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onUserNameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onDeptChanged: (String) -> Unit,
    onConfirmedPassword: (String) -> Unit,
){
    WindowSizeDecorator(
        onCompact = {
           Surface (
               modifier=modifier,
               shadowElevation = 6.dp,
           ){
               CompactModeRegistrationForm(
                   modifier = Modifier.padding(8.dp),
                   data = data,
                   fieldModifier = fieldModifier,
                   onNameChanged = onNameChanged,
                   onEmailChanged = onEmailChanged,
                   onUserNameChanged = onUserNameChanged,
                   onPasswordChanged = onPasswordChanged,
                   onDeptChanged = onDeptChanged,
                   onConfirmedPassword = onConfirmedPassword,

                   )
           }
        },
        onNonCompact = {
            Surface (
                modifier=modifier,
                shadowElevation = 6.dp,
            ){
                NonCompactModeRegistrationForm(
                    modifier = Modifier.padding(8.dp),
                    data = data,
                    fieldModifier = Modifier,
                    onNameChanged = onNameChanged,
                    onEmailChanged = onEmailChanged,
                    onUserNameChanged = onUserNameChanged,
                    onPasswordChanged = onPasswordChanged,
                    onDeptChanged = onDeptChanged,
                    onConfirmedPassword = onConfirmedPassword,
                )
            }

        }
    )


}