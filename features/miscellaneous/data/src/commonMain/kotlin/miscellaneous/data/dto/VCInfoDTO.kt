package miscellaneous.data.dto

import miscellaneous.domain.model.VCInfoModel


data class VCInfoDTO(
    val name: String,
    val details: String,
    val message: String,
    val imageUrl: String
){
    fun toModel()= VCInfoModel(
         name=name,
         details=details,
        message=message,
        imageUrl=imageUrl
    )
}
