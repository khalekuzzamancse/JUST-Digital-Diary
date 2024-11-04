package core.database.api

import core.database.factory.ApiFactory
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.fail

class ServerAcademicApiTest {
    private val api = ApiFactory.serverApi("")

    @Test
    fun facultyList() = executeTest {
        println(api.readFaculties())

    }
    @Test
    fun deptList() = executeTest {
        println(api.readDeptsUnderFaculty("01"))

    }
    @Test
    fun teacherList() = executeTest {
        println(api.readTeachersUnderDept("01"))

    }
    @Test
    fun teacherEntityList() = executeTest {
        val json=api.readTeachersUnderDept("01")
//       val entity= NetworkFactory.jsonParser().parseOrThrow(json,ListSerializer(TeacherReadEntity.serializer()))
//        println(entity)

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