@file:Suppress("spellCheckingInspection")
package faculty.data
import core.database.factory.ApiFactory
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.fail

class AdminRepositoryTest {

    @Test
    fun readFacultyByIdAsJson() {
        executeOrThrow {
            val response = ApiFactory.academicApi().readFacultyById("facultyofengineeringandtechnology")
            println("Response: $response")
        }
    }
    @Test
    fun readAllFacultyAsJson() {
        executeOrThrow {
            val response = ApiFactory.academicApi().readAllFaculty()
            println("Response: $response")
        }
    }
    @Test
    fun readAllFacultyAsEntity() {
        executeOrThrow {
            val parser = Json {
                ignoreUnknownKeys = true // This ignores fields that are not defined in the data class
            }
            val json = ApiFactory.academicApi().readAllFaculty()


        }
    }

    @Test
    fun readFacultyByIdByEntity() {
        executeOrThrow {
            val parser = Json {
               ignoreUnknownKeys = true // This ignores fields that are not defined in the data class
            }
            val json = ApiFactory.academicApi().readFacultyById("facultyofengineeringandtechnology")

        }
    }

    @Test
    fun readDeptByIdAsJson() {
        executeOrThrow {
            val response = ApiFactory.academicApi().readDeptById("computerscienceandengineering")
            println("Response: $response")
        }
    }
    @Test
    fun readDeptByIdByEntity() {
        executeOrThrow {

        }
    }
    @Test
    fun readReadTeacherByIdAsJson() {
        executeOrThrow {
            val response = ApiFactory.academicApi().readTeacherById("Prof. Dr. Syed Md. Galib_galib.cse@just.edu.bd")
            println("Response: $response")
        }
    }
    @Test
    fun readTeacherByIdByEntity() {
        executeOrThrow {
            val parser = Json {
                ignoreUnknownKeys = true // This ignores fields that are not defined in the data class
            }
            val json = ApiFactory.academicApi().readTeacherById("Prof. Dr. Syed Md. Galib_galib.cse@just.edu.bd")
//            val entity = parser.decodeFromString<TeacherEntryEntity>(json)
//            println("Response: $entity")
        }
    }


    private fun executeOrThrow(block: suspend () -> Unit) {
        runBlocking {
            try {
                block()
            } catch (e: Throwable) {
                fail("Failed for: $e")
            }
        }
    }
}
