package com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.registration.form

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.common_ui.WindowSizeDecorator

@Composable
internal fun RegistrationForm(
    modifier: Modifier = Modifier,
    fieldModifier: Modifier,
    data: RegistrationFormData,
    event: RegisterFormEvent,
) {
    WindowSizeDecorator(
        onCompact = {
            Surface(
                modifier = modifier,
                shadowElevation = 6.dp,
            ) {
                CompactModeRegistrationForm(
                    modifier = Modifier.padding(8.dp),
                    data = data,
                    fieldModifier = fieldModifier,
                    event = event

                )
            }
        },
        onNonCompact = {
            Surface(
                modifier = modifier,
                shadowElevation = 6.dp,
            ) {
                NonCompactModeRegistrationForm(
                    modifier = Modifier.padding(8.dp),
                    data = data,
                    fieldModifier = Modifier,
                    event=event
                )
            }

        }
    )


}