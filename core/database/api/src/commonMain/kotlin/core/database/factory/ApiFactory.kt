@file:Suppress("functionName")

package core.database.factory

import core.database.api.AcademicApiFacade
import core.database.api.AdministrationApiFacade
import core.database.api.dbapi.CalenderApiFacade
import core.database.api.dbapi.ScheduleApiFacade
import core.database.datasource.monggodb.factory.MongoDbFactory
import core.database.server.ServerFactory


object ApiFactory {
    // fun academicApi(): AcademicApiFacade = DbAcademicApiFacade(MongoDbFactory.academicApi())
//   fun academicApi(token:String?): AcademicApiFacade = serverApi(token)

    fun academicApi(token: String?): AcademicApiFacade = AcademicApiFacadeImpl(token = token)
    fun administrationApi(token: String?): AdministrationApiFacade = AdministrationApiFacadeImpl(
        remoteApi = if (token!=null)ServerFactory.administrationApi(token) else null
    )
    fun calenderApi(): CalenderApiFacade = CalenderApiFacade(MongoDbFactory.calenderApi())
    fun scheduleApi(): ScheduleApiFacade = ScheduleApiFacade(MongoDbFactory.scheduleApi())
    internal fun serverApi(token: String)= ServerFactory.serverApi(token)

}