package auth.ui.login.components.form_n_controls

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import auth.ui.login.components.controls.LoginControls
import auth.ui.login.components.form.LoginForm
import auth.ui.login.components.form.state.FormData
import auth.ui.login.components.form.state.LoginFormEvent
import auth.ui.login.components.event.LoginEvent

/**
 * A [Stateless Component]
 * For the Login destination.
 * @param modifier a [Modifier] (optional)
 * @param data for the  [LoginFormData]
 * @param event for the  [LoginFormEvent]
 */
@Composable
 internal fun LoginFormNControls(
    modifier: Modifier = Modifier,
    data: FormData,
    formEvent: LoginFormEvent,
    onControlEvent: (LoginEvent.LoginControlsEvent) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = Modifier,
            shadowElevation = 6.dp,
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                Box(Modifier.align(Alignment.CenterHorizontally)) {
                    LoginForm(
                        data = data,
                        fieldModifier = Modifier.fillMaxWidth(),
                        event = formEvent
                    )
                }
                Spacer(Modifier.height(16.dp))
                LoginControls(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    onEvent = onControlEvent

                )

            }
        }
    }
}
