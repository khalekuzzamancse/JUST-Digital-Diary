package core.database.monggodb

import core.database.monggodb.presentation.FacultyApiImpl
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.fail

class FacultyApiImplTest {

    /**
     * Test for reading all faculties from the API.
     */
    @Test
    fun testReadAll() {
        executeTest {
            val api = FacultyApiImpl()
            val response = api.readAll()
            println("Response: $response")
            // Add assertions as needed
        }
    }

    /**
     * Test for inserting a new faculty using the API.
     */
    @Test
    fun testInsertFaculty() {
        executeTest {
            val api = FacultyApiImpl()
            val json = """
                {
                    "priority": 1,
                    "name": "Faculty of XYZ"
                }
            """.trimIndent()

            val response = api.insert(json)
            println("Insert Response: $response")

        }
    }

    /**
     * Test for reading a specific faculty by ID using the API.
     */
    @Test
    fun testReadFacultyById() {
        executeTest {
            val api = FacultyApiImpl()
            val facultyId = "facultyofxyz"
            val response = api.read(facultyId)
            println("Response: $response")

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
