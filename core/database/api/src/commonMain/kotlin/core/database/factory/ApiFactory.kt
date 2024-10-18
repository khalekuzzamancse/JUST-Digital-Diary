@file:Suppress("functionName", "unused")

package core.database.factory

import core.database.api.AcademicApiFacade
import core.database.api.dbapi.DbAcademicApiFacade
import core.database.api.dbapi.CalenderApiFacade
import core.database.api.dbapi.ScheduleApiFacade
import core.database.api.serverapi.ServerAcademicApi
import core.database.datasource.monggodb.factory.MongoDbFactory

object ApiFactory {
    fun academicApi(): AcademicApiFacade = DbAcademicApiFacade(MongoDbFactory.academicApi())

    // fun academicApi(): AcademicApiFacade = serverApi()
    fun calenderApi(): CalenderApiFacade = CalenderApiFacade(MongoDbFactory.calenderApi())
    fun scheduleApi(): ScheduleApiFacade = ScheduleApiFacade(MongoDbFactory.scheduleApi())
    internal fun serverApi(): ServerAcademicApi =
        ServerAcademicApi(apiServiceClient = _networkIo(), parser = NetworkFactory.jsonParser())

    private fun _networkIo() = NetworkFactory.createAPIServiceClient()

}