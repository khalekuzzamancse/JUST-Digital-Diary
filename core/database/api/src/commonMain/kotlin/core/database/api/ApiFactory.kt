@file:Suppress("functionName")
package core.database.api

import data.monggodb.factory.MongoDbFactory
import domain.api.AcademicAdminApi

object ApiFactory {
    fun academicApi(): AcademicApi =AcademicApi(_concreteApi())
    private fun _concreteApi(): AcademicAdminApi=MongoDbFactory.academicApi()
}