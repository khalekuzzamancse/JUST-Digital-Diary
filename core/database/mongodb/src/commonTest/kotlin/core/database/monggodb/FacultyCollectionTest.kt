package core.database.monggodb

import core.database.monggodb.data.database.FacultyCollection
import core.database.monggodb.domain.model.FacultyEntity
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.fail

class FacultyCollectionTest {

    /**
     * Test for adding a faculty to the database.
     */
    @Test
    fun testAddFaculty() {
        executeTest {
            val faculty = FacultyEntity(name = "Faculty of Veterinary Medicine",priority = 8)
            FacultyCollection().addFaculty(faculty)
            println("Added successfully: $faculty")
        }
    }

    /**
     * Test for retrieving all faculties from the database.
     */
    @Test
    fun testGetAllFaculties() {
        executeTest {
            val faculties = FacultyCollection().getAllFaculties()
            println("Retrieved: $faculties")
        }
    }

    /**
     * Test for retrieving a specific faculty by ID.
     */
    @Test
    fun testReadFacultyById() {
        executeTest {
            val faculty = FacultyCollection().read("facultyofveterinarymedicine")
            println("Retrieved: $faculty")
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
                fail("Failed for: $e")
            }
        }
    }
}
