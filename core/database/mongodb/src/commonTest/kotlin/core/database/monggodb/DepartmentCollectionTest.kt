package core.database.monggodb

import core.database.monggodb.data.database.DepartmentCollection
import core.database.monggodb.domain.model.DepartmentEntity
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.fail

class DepartmentCollectionTest {

    /**
     * Test for creating a dummy department under a dummy faculty.
     */
    @Test
    fun testCreateDummyDepartment() {
        executeTest {
            // Create a dummy department entity
            val departmentEntity = DepartmentEntity(
                priority = 999,  // Dummy priority
                name = "Dummy Department of Testing",
                shortName = "DDT"
            )
            
            // Use a dummy faculty ID
            val dummyFacultyId = "dummyfacultyfortesting"  

            DepartmentCollection().create(dummyFacultyId, departmentEntity)
            println("Dummy department created successfully: $departmentEntity")
        }
    }

    /**
     * Test for reading all dummy departments.
     */
    @Test
    fun testReadAllDepartments() {
        executeTest {
            val departments = DepartmentCollection().readAll()
            println("Retrieved departments: $departments")
        }
    }

    /**
     * Test for reading dummy departments under a specific dummy faculty.
     */
    @Test
    fun testReadDepartmentsUnderDummyFaculty() {
        executeTest {
            val dummyFacultyId = "dummyfacultyfortesting"  // Dummy faculty ID
            val departments = DepartmentCollection().readUnderFaculty(dummyFacultyId)
            println("Dummy departments under faculty: $departments")
        }
    }

    /**
     * Test for reading a dummy department by its ID.
     */
    @Test
    fun testReadDummyDepartmentById() {
        executeTest {
            val dummyDeptId = "dummydepartmentoftesting"  // Dummy department ID
            val department = DepartmentCollection().readById(dummyDeptId)
            println("Retrieved dummy department: $department")
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
