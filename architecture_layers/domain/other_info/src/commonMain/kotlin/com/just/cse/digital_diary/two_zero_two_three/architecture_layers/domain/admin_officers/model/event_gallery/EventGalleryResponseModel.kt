package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_officers.model.event_gallery

interface EventGalleryResponseModel {
    data class Success(val events: List<EventGalleryModel>): EventGalleryResponseModel
    data class Failure(val reason: String?): EventGalleryResponseModel
}