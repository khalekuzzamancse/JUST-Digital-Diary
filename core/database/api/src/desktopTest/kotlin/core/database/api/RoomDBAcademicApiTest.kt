package core.database.api
import core.roomdb.factory.RoomDBFactory
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

class RoomDBAcademicApiTest {

   private val api=RoomDBFactory().createAcademicApi2()

    @Test
    fun read(): Unit = runBlocking {
       val response= api.readAllFaculty()
        println(response)
    }

}