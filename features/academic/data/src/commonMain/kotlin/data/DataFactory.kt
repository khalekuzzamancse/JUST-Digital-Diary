@file:Suppress("functionName")

package data

import data.repository.AdminRepositoryImpl
import data.repository.RepositoryImpl2
import data.service.JsonHandler
import data.service.JsonHandlerImpl
import factory.NetworkFactory
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
        RepositoryImpl2(
            jsonParser = _createJsonParser(),
            handler = JsonHandlerImpl(_createJsonParser()),
        )

    fun createAdminRepository(): AdminRepository = AdminRepositoryImpl(
        handler = JsonHandlerImpl(_createJsonParser()),
        jsonParser = _createJsonParser()
    )


    internal  fun jsonHandler():JsonHandler=JsonHandlerImpl(_createJsonParser())
    private fun _createJsonParser() = NetworkFactory.createJsonParser()
    private fun _createApiClient() = NetworkFactory.createAPIServiceClient()



}