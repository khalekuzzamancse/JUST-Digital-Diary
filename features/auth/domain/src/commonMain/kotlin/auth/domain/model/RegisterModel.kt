package auth.domain.model

data class RegisterModel(
    val name: String,
    val email: String,
    val username:String,
    val password:String
)
