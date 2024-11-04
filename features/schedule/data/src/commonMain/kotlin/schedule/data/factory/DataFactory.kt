@file:Suppress("functionName")
package schedule.data.factory

import factory.NetworkFactory
import schedule.data.repository.RepositoryImpl
import schedule.domain.repository.Repository
import schedule.data.service.JsonHandlerImpl

object DataFactory {
    fun repository(): Repository = RepositoryImpl(
        parser =_jsonParser(),
        handler = JsonHandlerImpl(_jsonParser())
    )
    private fun _jsonParser()= NetworkFactory.createJsonParser()
}