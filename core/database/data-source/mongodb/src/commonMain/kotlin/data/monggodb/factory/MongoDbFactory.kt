@file:Suppress("unused")
package data.monggodb.factory

import domain.api.AcademicApi
import domain.api.CalenderApi

object MongoDbFactory {
    fun academicApi(): AcademicApi= AcademicApiImpl()
    fun calenderApi(): CalenderApi = CalenderApiImpl()
}