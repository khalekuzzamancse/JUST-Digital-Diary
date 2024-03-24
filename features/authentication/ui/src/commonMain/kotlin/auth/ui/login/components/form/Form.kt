package auth.ui.login.components.form

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.common_ui.WindowSizeDecorator
import auth.ui.login.components.form.state.FormData
import auth.ui.login.components.form.state.LoginFormEvent

@Composable
internal fun LoginForm(
    fieldModifier: Modifier = Modifier,
    formModifier: Modifier = Modifier,
    data: FormData,
    event: LoginFormEvent,
) {
    WindowSizeDecorator(
        onCompact = {
            CompactScreenLoginForm(
                data = data,
                event = event,
                fieldModifier = fieldModifier,
                formModifier = formModifier,

            )
        },
        onNonCompact = {
            NonCompactScreenLoginForm(
                data = data,
                event=event,
                fieldModifier = Modifier.fillMaxWidth(),
                formModifier = formModifier,
            )
        }
    )

}