@file:Suppress("functionName")
package schedule.data.factory

import schedule.data.repository.RepositoryImpl
import schedule.domain.repository.Repository
import factory.NetworkFactory
import schedule.data.service.JsonHandlerImpl

object DataModuleFactory {
    fun createRepository(): Repository = RepositoryImpl(
        parser =_jsonParser(),
        handler = JsonHandlerImpl(_jsonParser())
    )
    private fun _jsonParser()= NetworkFactory.createJsonParser()
}