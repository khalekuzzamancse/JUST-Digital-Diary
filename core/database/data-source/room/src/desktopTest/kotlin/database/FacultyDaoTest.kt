@file:Suppress("SpellCheckingInspection")

package database
import core.roomdb.dao.FacultyDao
import core.roomdb.db.DB
import core.roomdb.schema.FacultySchema
import core.roomdb.factory.getDatabase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import kotlin.test.Test
import kotlin.test.assertNull

class FacultyDaoTest {

    private lateinit var db: DB
    private lateinit var facultyDao: FacultyDao

    @Before
    fun createDb() {
        db = getDatabase() // Assuming this function sets up the in-memory database
        facultyDao = db.facultyDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun testUpsertAndRetrieveFaculty() = runBlocking {
        val faculty = FacultySchema(
            id = 1,
            facultyId = "01",
            name = "Faculty of Engineering and Technology",
            departmentCount = 7
        )

        facultyDao.upsertFaculty(faculty)

        val retrievedFaculty = facultyDao.getFacultyById(faculty.facultyId)
        println("Inserted faculty: ${faculty.name}")
        println("Retrieved faculty: ${retrievedFaculty?.name}")

        assertEquals(faculty.name, retrievedFaculty?.name)
    }

    @Test
    fun testClearFaculties() = runBlocking {
        val faculty = FacultySchema(
            id = 1,
            facultyId = "01",
            name = "Faculty of Engineering and Technology",
            departmentCount = 7
        )

        facultyDao.upsertFaculty(faculty)
        println("Inserted faculty: ${faculty.name}")

        facultyDao.clearAllFaculties()

        val retrievedFaculty = facultyDao.getFacultyById(faculty.facultyId)
        println("Faculty after clearing: ${retrievedFaculty?.name}")

        assertNull(retrievedFaculty)
    }

    @Test
    fun testUpdateFaculty() = runBlocking {
        val faculty = FacultySchema(
            id = 1,
            facultyId = "01",
            name = "Faculty of Engineering and Technology",
            departmentCount = 7
        )

        facultyDao.upsertFaculty(faculty)
        println("Inserted faculty: ${faculty.name}")

        val updatedFaculty = faculty.copy(name = "Updated Faculty Name")
        facultyDao.upsertFaculty(updatedFaculty)
        println("Updated faculty: ${updatedFaculty.name}")

        val retrievedFaculty = facultyDao.getFacultyById(faculty.facultyId)
        println("Retrieved updated faculty: ${retrievedFaculty?.name}")

        assertEquals("Updated Faculty Name", retrievedFaculty?.name)
    }

    @Test
    fun testRetrieveEmptyDatabase() = runBlocking {
        val retrievedFaculties = facultyDao.getAllFaculties()
        println("Faculties from empty database: $retrievedFaculties")

        assertEquals(0, retrievedFaculties.size)
    }
}
