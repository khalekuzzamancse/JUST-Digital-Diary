package miscellaneous.domain.repoisitory

import miscellaneous.domain.model.AboutUsModel
import miscellaneous.domain.model.EventGalleryModel
import miscellaneous.domain.model.VCInfoModel

interface OtherInfoRepository{
    suspend fun getVCInfo(): Result<VCInfoModel>
    suspend fun getEvents(): Result<List<EventGalleryModel>>
}