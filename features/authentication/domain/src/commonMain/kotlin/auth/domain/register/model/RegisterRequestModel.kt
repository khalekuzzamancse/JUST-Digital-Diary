package auth.domain.register.model

data class RegisterRequestModel(
    val name: String,
    val email: String,
    val username:String,
    val password:String
)
