@file:Suppress("functionName")

package faculty.data

import faculty.domain.repository.AdminRepository
import faculty.domain.repository.Repository

object DataFactory {
    //    fun createPublicRepository(token: String?): Repository =
//        RepositoryImpl(
//            remoteSource = RemoteDataSourceImpl(
//                apiServiceClient = _createApiClient(),
//                jsonParser =_createJsonParser(),
//                jsonHandler = JsonHandlerImpl(_createJsonParser())
//            ),
//            token = token,
//        )
    fun createPublicRepository(token: String?): Repository =
        RepositoryImpl(
            token=token
        )

    fun createAdminRepository(): AdminRepository = AdminRepositoryImpl2()




}