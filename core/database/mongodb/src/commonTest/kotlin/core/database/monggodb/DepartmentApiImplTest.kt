package core.database.monggodb

import core.database.monggodb.presentation.DepartmentApiImpl
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.fail

class DepartmentApiImplTest {

    /**
     * Test for inserting a dummy department using the API.
     */
    @Test
    fun testInsertDummyDepartment() {
        executeTest {
            val departmentJson = """
                {
                    "priority": 999,
                    "name": "Dummy Department XYZ",
                    "shortname": "XYZ"
                }
            """.trimIndent()
            
            val dummyFacultyId = "dummyfacultyfortesting"  // Dummy faculty ID
            val api = DepartmentApiImpl()

            val response = api.insert(dummyFacultyId, departmentJson)
            println("Insert Response: $response")
        }
    }

    /**
     * Test for reading all departments using the API.
     */
    @Test
    fun testReadAllDepartments() {
        executeTest {
            val api = DepartmentApiImpl()
            val response = api.read()
            println("Read All Departments Response: $response")
        }
    }

    /**
     * Test for reading departments under a specific dummy faculty using the API.
     */
    @Test
    fun testReadDepartmentsUnderDummyFaculty() {
        executeTest {
            val dummyFacultyId = "dummyfacultyfortesting"  // Dummy faculty ID
            val api = DepartmentApiImpl()
            val response = api.readUnderFaculty(dummyFacultyId)
            println("Read Departments Under Dummy Faculty Response: $response")
        }
    }

    /**
     * Test for reading a department by its ID using the API.
     */
    @Test
    fun testReadDummyDepartmentById() {
        executeTest {
            val dummyDeptId = "dummydepartmentxyz"  // Dummy department ID
            val api = DepartmentApiImpl()
            val response = api.readById(dummyDeptId)
            println("Read Department By ID Response: $response")
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
