package core.database.realm.auth

data class SignedInUserResponseModel(
    val username: String,
    val password: String
)