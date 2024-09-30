package miscellaneous.data.repoisitory

import miscellaneous.data.source.LocalDataSource
import miscellaneous.data.source.RemoteDataSource
import miscellaneous.domain.model.EventGalleryModel
import miscellaneous.domain.model.VCInfoModel
import miscellaneous.domain.repoisitory.Repository

class RepositoryImpl internal constructor(
    private val token: String?,
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : Repository {
    override suspend fun getVCInfo(): Result<VCInfoModel> {
        if (token != null) {
            val result = remoteDataSource.getVCInfo(token)
            return if (result.isSuccess) result
            else {
                return localDataSource.getVCInfo()
            }
        }
        return localDataSource.getVCInfo()

    }

    override suspend fun getEvents(): Result<List<EventGalleryModel>> {
        if (token != null) {
            val result = remoteDataSource.getEvents(token)
            return if (result.isSuccess) result
            else {
                return localDataSource.getEvents()
            }
        }
        return localDataSource.getEvents()
    }
}