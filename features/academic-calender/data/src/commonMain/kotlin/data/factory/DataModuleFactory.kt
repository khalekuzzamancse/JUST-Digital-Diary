@file:Suppress("UnUsed")
package data.factory

import data.repository.CalenderRepositoryImpl
import data.service.CalendarServiceImpl
import data.service.UserServiceImpl
import domain.repository.CalenderRepository
import domain.service.CalendarService
import domain.service.UserService
import factory.NetworkFactory

object DataModuleFactory {
    fun createRepository(): CalenderRepository = CalenderRepositoryImpl(
            apiServiceClient = NetworkFactory.createAPIServiceClient(),
            jsonParser = NetworkFactory.createJsonParser()
        )

    fun createCalenderService(): CalendarService = CalendarServiceImpl()
    fun createUserService(): UserService = UserServiceImpl()
}