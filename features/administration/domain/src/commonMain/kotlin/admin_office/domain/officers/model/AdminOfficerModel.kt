package admin_office.domain.officers.model

data class AdminOfficerModel(
    val name: String,
    val email: String,
    val additionalEmail: String,
    val profileImage: String,
    val achievements: String,
    val phone: String?,
    val designations: String,
    val roomNo: String
)