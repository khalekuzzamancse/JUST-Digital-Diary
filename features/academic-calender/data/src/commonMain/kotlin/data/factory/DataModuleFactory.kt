@file:Suppress("UnUsed")
package data.factory

import data.repository.CalenderRepositoryImpl
import data.service.JsonHandlerImpl
import factory.NetworkFactory
import feature.academiccalender.domain.repository.CalenderRepository

object DataModuleFactory {
    fun repository(): CalenderRepository = CalenderRepositoryImpl(
        jsonParser = NetworkFactory.createJsonParser(),
        handler = JsonHandlerImpl(NetworkFactory.createJsonParser())
    )
}