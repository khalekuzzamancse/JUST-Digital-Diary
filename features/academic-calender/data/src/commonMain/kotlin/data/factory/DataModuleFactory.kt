@file:Suppress("UnUsed")
package data.factory

import core.database.factory.NetworkFactory
import data.repository.CalenderRepositoryImpl
import data.service.JsonHandlerImpl
import feature.academiccalender.domain.repository.CalenderRepository

object DataModuleFactory {
    fun repository(): CalenderRepository = CalenderRepositoryImpl(
        jsonParser = NetworkFactory.jsonParser(),
        handler = JsonHandlerImpl(NetworkFactory.jsonParser())
    )
}