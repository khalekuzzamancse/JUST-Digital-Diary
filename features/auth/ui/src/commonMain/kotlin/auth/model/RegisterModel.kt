package auth.model

internal data class RegisterModel(
    val name: String,
    val email: String,
    val username: String,
    val password: String,
    val confirmPassword: String
)