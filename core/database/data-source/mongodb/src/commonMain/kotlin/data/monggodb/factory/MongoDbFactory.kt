@file:Suppress("unused")
package data.monggodb.factory

import domain.api.AcademicApi
import domain.api.CalenderApi
import domain.api.ScheduleApi

object MongoDbFactory {
    fun academicApi(): AcademicApi= AcademicApiImpl()
    fun calenderApi(): CalenderApi = CalenderApiImpl()
    fun scheduleApi(): ScheduleApi = ScheduleApiImpl()
}