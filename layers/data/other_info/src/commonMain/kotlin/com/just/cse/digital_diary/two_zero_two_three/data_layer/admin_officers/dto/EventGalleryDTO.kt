package com.just.cse.digital_diary.two_zero_two_three.data_layer.admin_officers.dto

import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_officers.entity.event_gallery.EventGalleryModel

data class EventGalleryDTO(
    val name: String,
    val details:String,
    val imageLink:String
){
    fun toModel()=EventGalleryModel(
        name=name,
        details=details,
        imageLink=imageLink
    )
}