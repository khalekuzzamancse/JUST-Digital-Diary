package miscellaneous.controller_presenter

import miscellaneous.domain.model.EventGalleryModel
import miscellaneous.domain.model.VCInfoModel
import miscellaneous.controller_presenter.model.GalleryEventModel
import miscellaneous.controller_presenter.model.VCMessageModel

internal object ModelMapper {
    fun toUiModel(domainModel: EventGalleryModel) = GalleryEventModel(
        name = domainModel.name,
        imageLink = domainModel.imageLink,
        details = domainModel.details
    )
    fun toUiModel(domainModel: VCInfoModel) = VCMessageModel(
        name = domainModel.name,
        imageUrl = domainModel.imageUrl,
        details = domainModel.details,
        message = domainModel.message
    )
}