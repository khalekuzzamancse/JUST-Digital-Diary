package data.monggodb

import data.monggodb.db.FacultyCollection
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.fail

class FacultyCollectionTest {


    @Test
    fun insert() {
        executeTest {
            val json="""
                {
                  "priority": 0,
                  "name": "Dummy Facility"
                }
            """.trimIndent()
            println(FacultyCollection().insert(json))
        }
    }
    @Test
    fun update() {
        executeTest {
            //TODO:Insert it ,then take the id and then try
            val json="""
                {
                  "priority": 100,
                  "name": "Dummy Facility"
                }
            """.trimIndent()

            println(FacultyCollection().updateOrThrow(facultyId = "dummyfacility", json = json))
        }
    }




    @Test
    fun readAll() {
        executeTest {
            println(FacultyCollection().getAllFaculties())
        }
    }


    @Test
    fun testReadFacultyById() {
        executeTest {

            println( FacultyCollection().read("facultyofveterinarymedicine"))
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
