package core.database.api

import core.database.factory.ApiFactory
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.fail

class AcademicFacadeApiImplTest {
    @Test
    fun readFacultyFromCache()=executeTest{
        val api=ApiFactory.academicApi(null)
        val response=api.readFacultiesOrThrow()
        println(response)

    }
    @Test
    fun readDepartmentFromCache()=executeTest{
        val api=ApiFactory.academicApi(null)
        val response=api.readDeptsOrThrow("01")
        println(response)

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