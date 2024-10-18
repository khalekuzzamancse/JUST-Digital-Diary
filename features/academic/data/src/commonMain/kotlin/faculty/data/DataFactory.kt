@file:Suppress("functionName")

package faculty.data

import faculty.data.repository.AdminRepositoryImpl
import faculty.data.repository.RepositoryImpl
import faculty.data.service.JsonHandler
import faculty.data.service.JsonHandlerImpl
import core.database.factory.NetworkFactory
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
            jsonParser = _createJsonParser(),
            handler = JsonHandlerImpl(_createJsonParser()),
        )

    fun createAdminRepository(): AdminRepository = AdminRepositoryImpl(
        handler = JsonHandlerImpl(_createJsonParser()),
        jsonParser = _createJsonParser()
    )


    internal  fun jsonHandler(): JsonHandler = JsonHandlerImpl(_createJsonParser())
    private fun _createJsonParser() = NetworkFactory.jsonParser()
    private fun _createApiClient() = NetworkFactory.createAPIServiceClient()



}