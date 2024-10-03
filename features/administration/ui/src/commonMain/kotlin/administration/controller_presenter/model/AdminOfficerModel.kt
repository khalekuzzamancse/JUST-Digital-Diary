package administration.controller_presenter.model

data class AdminOfficerModel(
    val name: String,
    val email: String,
    val additionalEmail: String,
    val profileImageLink: String?,
    val achievements: String,
    val phone: String,
    val designations: String,
    val roomNo: String,
)