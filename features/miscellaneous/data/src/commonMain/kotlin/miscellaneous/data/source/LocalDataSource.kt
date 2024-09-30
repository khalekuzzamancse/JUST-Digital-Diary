package miscellaneous.data.source

import miscellaneous.domain.model.EventGalleryModel
import miscellaneous.domain.model.VCInfoModel


interface LocalDataSource {
    suspend fun getVCInfo(): Result<VCInfoModel>
    suspend fun getEvents(): Result<List<EventGalleryModel>>
}
