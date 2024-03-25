package miscellaneous.data.dto

import miscellaneous.domain.model.AboutUsModel


data class AboutUsDTO(
    val appName: String,
    val developedDepartmentName: String,
    val universityName: String,
    val otherInfo:String
) {
    fun toModel() = AboutUsModel(
        appName = appName,
        developedDepartmentName = developedDepartmentName,
        universityName = universityName,
        otherInfo=otherInfo
    )
}
