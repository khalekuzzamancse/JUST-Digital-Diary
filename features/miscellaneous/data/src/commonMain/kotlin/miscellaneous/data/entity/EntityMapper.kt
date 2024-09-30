package miscellaneous.data.entity

import miscellaneous.domain.model.EventGalleryModel
import miscellaneous.domain.model.VCInfoModel

internal object EntityMapper {
    fun toModel(entity: VCInfoEntity)=VCInfoModel(
        name = entity.name,
        message = entity.message,
        details = entity.details,
        imageUrl = entity.imageUrl
    )
    fun toModel(entity:EventGalleyEntity)=EventGalleryModel(
        name = entity.name,
        imageLink = entity.imageLink,
        details = entity.details,
    )
}