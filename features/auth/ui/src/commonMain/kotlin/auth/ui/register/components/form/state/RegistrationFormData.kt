package auth.ui.register.components.form.state

internal data class RegistrationFormData(
    val name: String,
    val email: String,
    val username: String,
    val dept:String,
    val password: String,
    val confirmPassword: String
)