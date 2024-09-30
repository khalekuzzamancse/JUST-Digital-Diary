@file:Suppress("functionName")
package data

import data.repository.RepositoryImpl
import data.service.JsonHandlerImpl
import data.source.RemoteDataSourceImpl
import factory.NetworkFactory
import faculty.domain.repository.Repository

object DataFactory {
    fun createFacultyListRepository(): Repository =
        RepositoryImpl(
            apiServiceClient = _createApiClient(),
            jsonParser = _createJsonParser(),
            remoteSource = RemoteDataSourceImpl(
                apiServiceClient = _createApiClient(),
                jsonParser =_createJsonParser(),
                jsonHandler = JsonHandlerImpl(_createJsonParser())
            )
        )


    private fun _createJsonParser()=NetworkFactory.createJsonParser()
    private fun _createApiClient()= NetworkFactory.createAPIServiceClient()


}