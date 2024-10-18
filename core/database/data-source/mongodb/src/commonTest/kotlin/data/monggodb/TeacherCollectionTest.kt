package data.monggodb


import core.database.datasource.monggodb.db.TeacherCollection
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.fail

class TeacherCollectionTest {
    private val teacherId = "johndoejohn.doe@university.com"
    private val deptId = "computerscience"

    @Test
    fun insert() {
        executeTest {
            val json = """
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
            println(TeacherCollection().insert(deptId, json))
        }
    }
    @Test
    fun update() {
        executeTest {
            //TODO: Insert first and make sure id is valid
            val json = """
                {
                    "priority": 111,
                    "name": "John Doe Updated",
                    "email": "john.doe@university.com",
                    "additional_email": "john.doe@gmail.com",
                    "achievements": "PhD in Computer Science",
                    "phone": "1234567890",
                    "designations": "Professor",
                    "room_no": "101"
                }
            """.trimIndent()
            println(TeacherCollection().updateOrThrow(teacherId, json))
        }
    }

    @Test
    fun testReadAllTeachers() {
        executeTest {
            println(TeacherCollection().readAll())
        }
    }

    /**
     * Test for reading teachers under a specific department.
     */
    @Test
    fun testReadTeachersUnderDepartment() {
        executeTest {
            val teachers = TeacherCollection().readByDeptId(deptId)
            println("Teachers under department: $teachers")
        }
    }


    @Test
    fun testReadTeacherById() {
        executeTest {
            println(TeacherCollection().readById(teacherId))
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
