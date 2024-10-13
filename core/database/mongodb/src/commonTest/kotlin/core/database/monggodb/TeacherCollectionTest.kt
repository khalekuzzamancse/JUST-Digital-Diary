package core.database.monggodb

import core.database.monggodb.data.database.TeacherCollection
import core.database.monggodb.domain.model.TeacherEntity
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.fail

class TeacherCollectionTest {

    /**
     * Test for inserting a dummy teacher.
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
                    "name": "John Doe",
                    "email": "john.doe@university.com",
                    "additional_email": "john.doe@gmail.com",
                    "achievements": "PhD in Computer Science",
                    "phone": "1234567890",
                    "designations": "Professor",
                    "room_no": "101"
                }
            """.trimIndent()

            // Parse the JSON string into a TeacherEntity object using kotlinx.serialization
            val teacherEntity = Json.decodeFromString(TeacherEntity.serializer(), teacherJson)

            // Insert the dummy teacher into the collection
            TeacherCollection().add(deptId, teacherEntity)
            println("Teacher inserted successfully: $teacherEntity")
        }
    }

    /**
     * Test for reading all teachers.
     */
    @Test
    fun testReadAllTeachers() {
        executeTest {
            val teachers = TeacherCollection().read()
            println("Retrieved teachers: $teachers")
        }
    }

    /**
     * Test for reading teachers under a specific department.
     */
    @Test
    fun testReadTeachersUnderDepartment() {
        executeTest {
            val deptId = "computerscience"  // Dummy department ID
            val teachers = TeacherCollection().read(deptId)
            println("Teachers under department: $teachers")
        }
    }

    /**
     * Test for reading a teacher by ID.
     */
    @Test
    fun testReadTeacherById() {
        executeTest {
            val teacherId = "John Doe_john.doe@university.com"  // Dummy teacher ID
            val teacher = TeacherCollection().readById(teacherId)
            println("Retrieved teacher by ID: $teacher")
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
