@file:Suppress("functionName", "unused","UNUSED_PARAMETER")

package core.database.factory

import core.database.api.AcademicApiFacade
import core.database.api.dbapi.CalenderApiFacade
import core.database.api.dbapi.ScheduleApiFacade
import core.database.api.room.RoomAcademicApiFacade
import core.database.api.serverapi.ServerAcademicApi
import core.database.datasource.monggodb.factory.MongoDbFactory
import core.roomdb.factory.RoomDBFactory
import  core.roomdb.factory.getRoomDBFactory

object ApiFactory {
   // fun academicApi(): AcademicApiFacade = DbAcademicApiFacade(MongoDbFactory.academicApi())
//   fun academicApi(token:String?): AcademicApiFacade = serverApi(token)

   fun academicApi(token:String?): AcademicApiFacade = AcademicApiFacadeImpl(token)
    fun calenderApi(): CalenderApiFacade = CalenderApiFacade(MongoDbFactory.calenderApi())
    fun scheduleApi(): ScheduleApiFacade = ScheduleApiFacade(MongoDbFactory.scheduleApi())
    internal fun serverApi(token:String): ServerAcademicApi =
        ServerAcademicApi(token=token,apiServiceClient = _networkIo(), parser = NetworkFactory.jsonParser())


    private fun _networkIo() = NetworkFactory.createAPIServiceClient()
   internal fun roomAcademicApiFacade() :AcademicApiFacade{
       val roomDBFactory: RoomDBFactory =getRoomDBFactory()
       return RoomAcademicApiFacade(roomDBFactory.createAcademicApi2())
   }

}