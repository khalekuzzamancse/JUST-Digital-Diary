package data.monggodb

import core.database.datasource.monggodb.db.DepartmentCollection
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.fail

class DepartmentCollectionTest {


    @Test
    fun insert() {
        executeTest {
            val json = """
                {
                  "priority": 2,
                  "name": "Information Technology Department",
                  "shortname": "IT"
                }
            """.trimIndent()

            println( DepartmentCollection().insert("dummy", json))

        }
    }
    @Test
    fun update() {
        //TODO: insert first then make sure id is exists
        executeTest {
            val json = """
                {
                  "priority": 200,
                  "name": "Information Technology Department",
                  "shortname": "ITTX"
                }
            """.trimIndent()

            println( DepartmentCollection().updateOrThrow(deptId = "physicsdepartment", json = json))

        }
    }

    @Test
    fun testReadAllDepartments() {
        executeTest {
            println(DepartmentCollection().readAll())
        }
    }

    @Test
    fun testReadDepartmentsUnderDummyFaculty() {
        executeTest {
            val dummyFacultyId = "facultyofengineeringandtechnology"
            println( DepartmentCollection().readUnderFaculty(dummyFacultyId))
        }
    }

    @Test
    fun testReadDummyDepartmentById() {
        executeTest {
            val dummyDeptId = "electricalandelectronicengineering"
            println(DepartmentCollection().readById(dummyDeptId))
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
