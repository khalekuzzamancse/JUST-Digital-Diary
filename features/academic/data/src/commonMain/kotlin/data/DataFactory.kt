@file:Suppress("functionName")
package data

import data.repository.AdminRepositoryImpl
import data.repository.RepositoryImpl
import data.service.JsonHandlerImpl
import data.source.RemoteDataSourceImpl
import factory.NetworkFactory
import faculty.domain.repository.AdminRepository
import faculty.domain.repository.Repository

object DataFactory {
    fun createPublicRepository(token: String?,): Repository =
        RepositoryImpl(
            remoteSource = RemoteDataSourceImpl(
                apiServiceClient = _createApiClient(),
                jsonParser =_createJsonParser(),
                jsonHandler = JsonHandlerImpl(_createJsonParser())
            ),
            token = token,
        )
    fun createAdminRepository(): AdminRepository = AdminRepositoryImpl(
        apiService = _createApiClient(),
        jsonHandler =  JsonHandlerImpl(_createJsonParser()),
        jsonParser = _createJsonParser()
    )


    private fun _createJsonParser()=NetworkFactory.createJsonParser()
    private fun _createApiClient()= NetworkFactory.createAPIServiceClient()


}