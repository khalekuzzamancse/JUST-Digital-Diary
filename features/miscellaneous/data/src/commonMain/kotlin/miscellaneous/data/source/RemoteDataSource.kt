package miscellaneous.data.source

import miscellaneous.domain.model.EventGalleryModel
import miscellaneous.domain.model.VCInfoModel


internal interface RemoteDataSource {
    suspend fun getVCInfo(token:String): Result<VCInfoModel>
    suspend fun getEvents(token: String): Result<List<EventGalleryModel>>
}
