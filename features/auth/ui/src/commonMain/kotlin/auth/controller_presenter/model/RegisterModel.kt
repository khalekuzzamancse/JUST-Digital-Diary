package auth.controller_presenter.model

internal data class RegisterModel(
    val name: String,
    val email: String,
    val username: String,
    val password: String,
    val confirmPassword: String
)