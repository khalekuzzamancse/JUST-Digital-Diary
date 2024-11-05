package core.database.api

import core.data.entity.academic.FacultyReadEntity
import core.data.entity.academic.FacultyWriteEntity
import core.roomdb.factory.getRoomDBFactory
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.fail

class RoomApiTest {
    private val api = getRoomDBFactory().createAcademicCacheApi()

    @Test
    fun insertFaculties() = executeTest {
        api.insertFacultiesOrThrow(
            listOf(
                FacultyReadEntity(
                    priority = 1,
                    name = "XYZ",
                    faculty_id = "01",
                    number_of_dept = 0
                )
            )
        )
        val result = api.readFacultiesOrThrow()
        println("$result")

    }
    @Test
    fun readFaculties() = executeTest {
        val result = api.readFacultiesOrThrow()
        println("$result")

    }

    @Test
    fun readDepartments() = executeTest {

        val result = api.readDeptsOrThrow("01")
        println("$result")

    }

    @Test
    fun readTeachers() = executeTest {
        val result = api.readTeachersOrThrow("01")
        println("$result")

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