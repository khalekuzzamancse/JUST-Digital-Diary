package faculty.data



import core.database.api.ApiFactory
import data.entity.admin.DepartmentEntryEntity
import data.entity.admin.FacultyEntryEntity
import data.entity.admin.TeacherEntryEntity
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
            val entity = parser.decodeFromString<List<FacultyEntryEntity>>(json)
            println("Response: $entity")
        }
    }

    @Test
    fun readFacultyByIdByEntity() {
        executeOrThrow {
            val parser = Json {
               ignoreUnknownKeys = true // This ignores fields that are not defined in the data class
            }
            val json = ApiFactory.academicApi().readFacultyById("facultyofengineeringandtechnology")
            val entity = parser.decodeFromString<FacultyEntryEntity>(json)
            println("Response: $entity")
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
            val parser = Json {
                ignoreUnknownKeys = true // This ignores fields that are not defined in the data class
            }
            val json = ApiFactory.academicApi().readDeptById("computerscienceandengineering")
            val entity = parser.decodeFromString<DepartmentEntryEntity>(json)
            println("Response: $entity")
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
            val entity = parser.decodeFromString<TeacherEntryEntity>(json)
            println("Response: $entity")
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
