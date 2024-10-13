package core.database.monggodb

import core.database.monggodb.presentation.TeacherApiImpl
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.fail

class TeacherApiImplTest {

    /**
     * Test for inserting a dummy teacher using the API.
     */
    @Test
    fun testInsertDummyTeacher() {
        executeTest {
            // Dummy department ID
            val deptId = "computerscience"

            // Create dummy teacher JSON
            val teacherJson = """
                {
                    "priority": 1,
                    "name": "John Doe2",
                    "email": "john.doe@university.com",
                    "additional_email": "john.doe@gmail.com",
                    "achievements": "PhD in Computer Science",
                    "phone": "1234567890",
                    "designations": "Professor",
                    "room_no": "101"
                }
            """.trimIndent()

            // Call the API to insert the dummy teacher
            val api = TeacherApiImpl()
            val response = api.insert(deptId, teacherJson)
            println("Insert Response: $response")
        }
    }

    /**
     * Test for reading all teachers using the API.
     */
    @Test
    fun testReadAllTeachers() {
        executeTest {
            val api = TeacherApiImpl()
            val response = api.readAll()
            println("Read All Teachers Response: $response")
        }
    }

    /**
     * Test for reading teachers under a specific department using the API.
     */
    @Test
    fun testReadTeachersUnderDepartment() {
        executeTest {
            val deptId = "computerscience"  // Dummy department ID
            val api = TeacherApiImpl()
            val response = api.readUnderDept(deptId)
            println("Read Teachers Under Department Response: $response")
        }
    }

    /**
     * Test for reading a teacher by ID using the API.
     */
    @Test
    fun testReadTeacherById() {
        executeTest {
            val teacherId = "John Doe2_john.doe@university.com"  // Dummy teacher ID
            val api = TeacherApiImpl()
            val response = api.readById(teacherId)
            println("Read Teacher By ID Response: $response")
        }
    }

    /**
     * Helper method to execute tests with error handling.
     */
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
