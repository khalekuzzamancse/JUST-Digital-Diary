@file:Suppress("functionName","unused")
package core.database.api

import data.monggodb.factory.MongoDbFactory

object ApiFactory {
    fun academicApi(): AcademicApiFacade =AcademicApiFacade(MongoDbFactory.academicApi())
    fun calenderApi(): CalenderApiFacade =CalenderApiFacade(MongoDbFactory.calenderApi())
    fun scheduleApi(): ScheduleApiFacade =ScheduleApiFacade(MongoDbFactory.scheduleApi())

}