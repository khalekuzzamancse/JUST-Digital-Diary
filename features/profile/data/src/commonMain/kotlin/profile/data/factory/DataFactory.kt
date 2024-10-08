@file:Suppress("functionName","unused")
package profile.data.factory

import profile.data.repository.RepositoryImpl
import profile.data.services.JsonHandler
import domain.repository.Repository
import factory.NetworkFactory

object DataFactory {
    fun createRepository(token: String): Repository = RepositoryImpl(
        token = token,
        apiServiceClient = NetworkFactory.createAPIServiceClient(),
        jsonParser = NetworkFactory.createJsonParser(),
        jsonHandler = _createJsonHandler()
    )

    private fun _createJsonHandler(): JsonHandler =
        JsonHandlerImpl(NetworkFactory.createJsonParser())

}