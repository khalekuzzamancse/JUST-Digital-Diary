package auth.ui.login.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import auth.ui.login.components.event.LoginEvent
import auth.ui.login.components.form.state.FormData
import auth.ui.login.components.form.state.LoginFormEvent
import auth.ui.login.components.form_n_controls.LoginFormNControls

@Composable
fun LoginDestination(
    viewModel: LoginDestinationViewModel,
    modifier: Modifier = Modifier,
    onEvent: (LoginEvent) -> Unit,
) {
    val data = viewModel.formData.collectAsState().value
    val formEvent = viewModel.formEvent
    LoginDestination(
        modifier = modifier,
        data = data,
        formEvent = formEvent,
        onEvent = onEvent
    )

}

@Composable
private fun LoginDestination(
    modifier: Modifier = Modifier,
    data: FormData,
    formEvent: LoginFormEvent,
    onEvent: (LoginEvent) -> Unit,
) {
    LoginFormNControls(
        modifier = modifier,
        data = data,
        onControlEvent = onEvent,
        formEvent = formEvent
    )
}

