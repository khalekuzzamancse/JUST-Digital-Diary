@file:Suppress("unused")
package core.database.datasource.monggodb.factory

import domain.api.AcademicApiDeprecated
import domain.api.CalenderApi
import domain.api.ScheduleApi

object MongoDbFactory {
    fun academicApi(): AcademicApiDeprecated= AcademicApiDeprecatedImpl()
    fun calenderApi(): CalenderApi = CalenderApiImpl()
    fun scheduleApi(): ScheduleApi = ScheduleApiImpl()
}