@file:Suppress("functionName")

package core.database.factory
import core.roomdb.factory.getRoomDBFactory
import core.database.api.AcademicApiFacade
import core.database.api.AdministrationApiFacade
import core.database.api.dbapi.CalenderApiFacade
import core.database.api.dbapi.ScheduleApiFacade
import core.database.datasource.monggodb.factory.MongoDbFactory
import core.database.server.ServerFactory
object ApiFactory {
    fun academicApi(token: String?): AcademicApiFacade = AcademicApiFacadeImpl(
        remoteApi = token?.let { ServerFactory.serverApi2(it) },
        cacheApi = getRoomDBFactory().createAcademicApi2()
    )
    fun administrationApi(token: String?): AdministrationApiFacade = AdministrationApiFacadeImpl(
        remoteApi = if (token!=null)ServerFactory.administrationApi(token) else null
    )
    fun calenderApi(): CalenderApiFacade = CalenderApiFacade(MongoDbFactory.calenderApi())
    fun scheduleApi(): ScheduleApiFacade = ScheduleApiFacade(MongoDbFactory.scheduleApi())
    internal fun serverApi(token: String)= ServerFactory.serverApi(token)

}