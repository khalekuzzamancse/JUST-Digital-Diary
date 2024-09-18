package miscellaneous.data.repoisitory

import miscellaneous.data.data_sources.remote.RemoteDataSource
import miscellaneous.domain.model.AboutUsModel
import miscellaneous.domain.model.EventGalleryModel
import miscellaneous.domain.model.VCInfoModel
import miscellaneous.domain.repoisitory.OtherInfoRepository


class OtherInfoRepositoryImpl: OtherInfoRepository {

    override suspend fun getVCInfo(): Result<VCInfoModel> {
       return Result.success(RemoteDataSource().getVCInfo().toModel())
    }

    override suspend fun getAboutUs(): Result<AboutUsModel> {
        return Result.success(RemoteDataSource().getAboutUsInfo().toModel())
    }

    override suspend fun getEvents(): Result<List<EventGalleryModel>> {
        return Result.success(RemoteDataSource().getEvents().map { it.toModel() })
    }

}