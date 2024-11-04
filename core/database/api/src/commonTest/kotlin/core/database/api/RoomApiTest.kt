package core.database.api
import core.roomdb.factory.getRoomDBFactory
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.fail

class RoomApiTest {
    private val api= getRoomDBFactory().createAcademicApi2()
    @Test
    fun testReadAllAcademicCalendars()=executeTest {
            try {
                val result = api.readFaculties()
                println("All calendars: $result")
            } catch (e: Exception) {
                fail("Reading all calendars failed: ${e.message}")
            }
        }

    private fun executeTest(block: suspend () -> Unit) {
        runBlocking {
            try {
                block()
            } catch (e: Throwable) {
                fail("Test failed due to: $e")
            }
        }
    }
}