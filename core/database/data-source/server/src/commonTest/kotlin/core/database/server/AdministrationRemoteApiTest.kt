package core.database.server
import core.database.server.ServerFactory.administrationApi
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.fail

class AdministrationRemoteApiTest {
    private val api = administrationApi(token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOiJmMzFlNGVmYS1lYTBhLTRmYTItOSIsInJvbGVfaWQiOjEsInVzZXJuYW1lIjoia2hhbGVrMTkwMTQyIiwiaWF0IjoxNzMwNzMxMDA2LCJleHAiOjE3MzEzMzU4MDZ9.mj-xV3gxjUDPYvoWx0HfYJuu2Eu1SfvtQEi1KCWN01s")

    @Test
    fun readOffices() = executeTest {
        println(api.readOfficesOrThrow())
    }
    @Test
    fun readSubOffices() = executeTest {
        println(api.readSubOfficesOrThrow("01"))
    }
    @Test
    fun readEmployee() = executeTest {
        println(api.readEmployeesOrThrow("01"))
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