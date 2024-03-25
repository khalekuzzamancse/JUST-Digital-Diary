package auth.ui.login.components.form.state

 data class LoginFormEvent(
    val onUserNameChanged: (String) -> Unit,
    val onPasswordChanged: (String) -> Unit,
)
