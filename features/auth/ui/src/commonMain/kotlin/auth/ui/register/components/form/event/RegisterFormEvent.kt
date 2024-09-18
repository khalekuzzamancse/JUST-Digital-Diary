package auth.ui.register.components.form.event

internal data class RegisterFormEvent(
    val onNameChanged: (String) -> Unit,
    val onEmailChanged: (String) -> Unit,
    val onUserNameChanged: (String) -> Unit,
    val onPasswordChanged: (String) -> Unit,
    val onDeptChanged: (String) -> Unit,
    val onConfirmedPassword: (String) -> Unit,
)