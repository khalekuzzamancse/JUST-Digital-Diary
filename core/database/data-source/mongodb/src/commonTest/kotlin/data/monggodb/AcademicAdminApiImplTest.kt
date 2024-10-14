package data.monggodb
import data.monggodb.factory.AcademicAdminApiImpl
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.fail
class AcademicAdminApiImplTest {

    private val api = AcademicAdminApiImpl()
    private val facultyId = "facultyofscience"
    private val deptId = "departmentofphysics"
    private val teacherId = "johndoejohn.doe@university.com"

    @Test
    fun testReadAllFaculty() {
        executeTest {
            println(api.readAllFaculty())
        }
    }

    @Test
    fun testInsertFaculty() {
        executeTest {
            val json = """
                {
                    "priority": 1,
                    "name": "Science Faculty"
                }
            """.trimIndent()
            println(api.insertFaculty(json))
        }
    }

    @Test
    fun testReadFacultyById() {
        executeTest {
            println(api.readFacultyById(facultyId))
        }
    }

    @Test
    fun testInsertDepartment() {
        executeTest {
            val json = """
                {
                    "priority": 1,
                    "name": "Physics Department",
                    "shortname": "PHY"
                }
            """.trimIndent()
            println(api.insertDept(facultyId, json))
        }
    }

    @Test
    fun testReadAllDepartments() {
        executeTest {
            println(api.readAllDept())
        }
    }

    @Test
    fun testReadDepartmentsUnderFaculty() {
        executeTest {
            println(api.deptUnderFaculty(facultyId))
        }
    }

    @Test
    fun testReadDepartmentById() {
        executeTest {
            println(api.readDeptById(deptId))
        }
    }

    @Test
    fun testInsertTeacher() {
        executeTest {
            val json = """
                {
                    "priority": 1,
                    "name": "John Doe",
                    "email": "john.doe@university.com",
                    "additional_email": "john.doe@gmail.com",
                    "achievements": "PhD in Physics",
                    "phone": "1234567890",
                    "designations": "Professor",
                    "room_no": "101"
                }
            """.trimIndent()
            println(api.insertTeacher(deptId, json))
        }
    }

    @Test
    fun testReadAllTeachers() {
        executeTest {
            println(api.readAllTeacher())
        }
    }

    @Test
    fun testReadTeachersUnderDepartment() {
        executeTest {
            println(api.readTeachersUnderDept(deptId))
        }
    }

    @Test
    fun testReadTeacherById() {
        executeTest {
            println(api.readTeacherById(teacherId))
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
