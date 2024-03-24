package auth.ui.register.components.form

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import auth.ui.register.components.form.event.RegisterFormEvent
import auth.ui.register.components.form.state.RegistrationFormData
import com.just.cse.digital_diary.two_zero_two_three.common_ui.WindowSizeDecorator

@Composable
internal fun RegistrationForm(
    modifier: Modifier = Modifier,
    fieldModifier: Modifier,
    data: RegistrationFormData,
    event: RegisterFormEvent,
) {
    val formPadding=8.dp
    WindowSizeDecorator(
        onCompact = {
            Surface(
                modifier = modifier,
                shadowElevation = 6.dp,
            ) {
                CompactModeRegistrationForm(
                    modifier = Modifier.padding(formPadding).fillMaxWidth(),
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
                    modifier = Modifier.padding(formPadding).fillMaxWidth(),
                    data = data,
                    fieldModifier = Modifier.fillMaxWidth(),
                    event=event
                )
            }

        }
    )


}