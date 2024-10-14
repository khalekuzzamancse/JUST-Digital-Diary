@file:Suppress("unused")
package data.monggodb.factory

import domain.api.AcademicAdminApi

object MongoDbFactory {
    fun academicApi(): AcademicAdminApi= AcademicAdminApiImpl()
}