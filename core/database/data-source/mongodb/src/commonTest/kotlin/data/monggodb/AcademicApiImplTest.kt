@file:Suppress("spellCheckingInspection")
package data.monggodb
import core.database.datasource.monggodb.factory.AcademicApiImpl
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.fail
class AcademicApiImplTest {

    private val api = AcademicApiImpl()
    private val facultyId = "facultyofscience"
    private val deptId = "departmentofphysics"
    private val teacherId = "johndoejohn.doe@university.com"

    @Test
    fun testReadAllFaculty() {
        executeTest {
            println(api.readFaculties())
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
            println(api.upsetFacultyList(json))
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
            println(api.readDeptsUnderFaculty(facultyId))
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
                    "room_no": "101",
                    "image_link": "no link"
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

    @Test
    fun deleteFacultyTest()= executeTest {
            println(api.deleteFaculty(id = "15oct"))
    }
    @Test
    fun deleteDepartmentTest()= executeTest {
        println(api.deleteDepartment(id = "physicsdepartment"))
    }
    @Test
    fun deleteTeacherTest()= executeTest {
        println(api.deleteTeacher(id = "johndoejohn.doe@university.com"))
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
