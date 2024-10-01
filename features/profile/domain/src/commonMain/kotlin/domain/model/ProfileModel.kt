package domain.model

data class ProfileModel(
    val name: String,
    val username: String,
    val email: String
){

    fun isEmpty()=name.isBlank()&&username.isBlank()&&email.isBlank()
}
