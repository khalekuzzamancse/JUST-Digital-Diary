package core.database.server
import core.database.server.ServerFactory.administrationApi
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.fail

class AcademicRemoteApiTest {
    private val api = ServerFactory.serverApi2(token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOiJmMzFlNGVmYS1lYTBhLTRmYTItOSIsInJvbGVfaWQiOjEsInVzZXJuYW1lIjoia2hhbGVrMTkwMTQyIiwiaWF0IjoxNzMwNzMxMDA2LCJleHAiOjE3MzEzMzU4MDZ9.mj-xV3gxjUDPYvoWx0HfYJuu2Eu1SfvtQEi1KCWN01s")

    @Test
    fun readFaculties() = executeTest {
        println(api.readFacultiesOrThrow())
    }
    @Test
    fun readDepartments() = executeTest {
        println(api.readDeptsOrThrow("01"))
    }
    @Test
    fun readEmployee() = executeTest {
        println(api.readTeachersOrThrow("01"))
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