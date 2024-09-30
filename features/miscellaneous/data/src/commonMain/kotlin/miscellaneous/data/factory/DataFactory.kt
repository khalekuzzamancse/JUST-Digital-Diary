@file:Suppress("functionName","unused")

package miscellaneous.data.factory

import factory.NetworkFactory
import miscellaneous.data.repoisitory.RepositoryImpl
import miscellaneous.data.services.JsonHandler
import miscellaneous.data.source.LocalDataSource
import miscellaneous.data.source.RemoteDataSource
import miscellaneous.domain.repoisitory.Repository

object DataFactory {
    fun createRepository(token: String?): Repository = RepositoryImpl(
        token = token,
        localDataSource = _createLocalDataSource(),
        remoteDataSource = _createRemoteDataSource()
    )

    private fun _createRemoteDataSource(): RemoteDataSource =
        RemoteDataSourceImpl(
            apiServiceClient = NetworkFactory.createAPIServiceClient(),
            jsonParser = NetworkFactory.createJsonParser(),
            jsonHandler = _createJsonHandler()
        )

    private fun _createLocalDataSource(): LocalDataSource = LocalDataSourceImpl()
    private fun _createJsonHandler(): JsonHandler =
        JsonHandlerImpl(NetworkFactory.createJsonParser())

}