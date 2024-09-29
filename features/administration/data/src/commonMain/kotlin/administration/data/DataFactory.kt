@file:Suppress("functionName")

package administration.data

import admin_office.domain.repository.Repository
import administration.data.repository.RepositoryImpl
import administration.data.service.JsonHandlerImpl
import administration.data.source.RemoteDataSource
import administration.data.source.RemoteDataSourceImpl
import factory.NetworkFactory


object DataFactory {

    fun createRepository(token: String?): Repository = RepositoryImpl(
        token = token,
        remoteSource = _createRemoteDataSource()
    )

    private fun _createRemoteDataSource(): RemoteDataSource = RemoteDataSourceImpl(
        apiServiceClient = _createApiClient(),
        jsonParser = _createJsonParser(),
        jsonHandler = JsonHandlerImpl(_createJsonParser())
    )
    private fun _createJsonParser() = NetworkFactory.createJsonParser()
    private fun _createApiClient() = NetworkFactory.createAPIServiceClient()


}