@file:Suppress("UnUsed")
package data.factory

import data.misc.JsonHandlerImpl
import data.repository.CalenderRepositoryImpl
import data.service.CalendarServiceImpl
import data.service.UserServiceImpl
import feature.academiccalender.domain.repository.CalenderRepository
import feature.academiccalender.domain.service.CalendarService
import feature.academiccalender.domain.service.UserService
import core.database.factory.NetworkFactory

object DataModuleFactory {
    fun createRepository(): CalenderRepository = CalenderRepositoryImpl(
        jsonParser = NetworkFactory.jsonParser(),
        handler = JsonHandlerImpl(NetworkFactory.jsonParser())
    )

    fun createCalenderService(): CalendarService = CalendarServiceImpl()
    fun createUserService(): UserService = UserServiceImpl()
}