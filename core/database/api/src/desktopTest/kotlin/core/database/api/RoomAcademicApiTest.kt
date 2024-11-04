package core.database.api
import core.roomdb.factory.RoomDBFactory
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

class RoomAcademicApiTest {

   private val api=RoomDBFactory().createAcademicApi2()

    @Test
    fun readFaculties(): Unit = runBlocking {
       val response= api.readFaculties()
        println(response)
    }
    @Test
    fun readDepartmentByFaculty(): Unit = runBlocking {
        val response= api.readDeptsUnderFaculty("01")
        println(response)
    }
    @Test
    fun readTeacherUnderDept(): Unit = runBlocking {
        val response= api.readTeachersUnderDept("01")
        println(response)
    }

}