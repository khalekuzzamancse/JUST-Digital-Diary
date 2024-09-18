package miscellaneous.data.dto

import miscellaneous.domain.model.EventGalleryModel


data class EventGalleryDTO(
    val name: String,
    val details:String,
    val imageLink:String
){
    fun toModel()= EventGalleryModel(
        name=name,
        details=details,
        imageLink=imageLink
    )
}