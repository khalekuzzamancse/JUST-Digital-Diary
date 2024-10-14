@file:Suppress("SpellCheckingInspection")

package database

import core.database.db.Database
import core.database.getDatabase
import database.schema.DepartmentSchema
import database.schema.FacultySchema
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import kotlin.test.Test
import kotlin.test.assertNull

class DepartmentSubSchemaDaoTest {

    private lateinit var db: Database
    private lateinit var departmentDao: core.database.dao.DepartmentDao
    private lateinit var facultyDao: core.database.dao.FacultyDao

    // Provided sample departments data
    private val sampleDepartments = listOf(
        DepartmentSchema(
            id = 1,
            deptId = "01",
            shortname = "CSE",
            name = "Computer Science and Engineering",
            membersCount = 25,
            facultyId = "01"
        ),
        DepartmentSchema(
            id = 2,
            deptId = "11",
            shortname = "EEE",
            name = "Electrical and Electronic Engineering",
            membersCount = 17,
            facultyId = "01"
        ),
        DepartmentSchema(
            id = 51,
            deptId = "07",
            shortname = "IPE",
            name = "Industrial and Production Engineering",
            membersCount = 18,
            facultyId = "01"
        ),
        DepartmentSchema(
            id = 52,
            deptId = "22",
            shortname = "TE",
            name = "Textile Engineering",
            membersCount = 9,
            facultyId = "01"
        ),
        DepartmentSchema(
            id = 53,
            deptId = "05",
            shortname = "CHE",
            name = "Chemical Engineering",
            membersCount = 20,
            facultyId = "01"
        ),
        DepartmentSchema(
            id = 54,
            deptId = "08",
            shortname = "PME",
            name = "Petroleum and Mining Engineering",
            membersCount = 13,
            facultyId = "01"
        ),
        DepartmentSchema(
            id = 55,
            deptId = "19",
            shortname = "BME",
            name = "Biomedical Engineering",
            membersCount = 9,
            facultyId = "01"
        )
    )

    @Before
    fun createDb() {
        db = getDatabase() // Assuming this sets up the in-memory database
        departmentDao = db.departmentDao()
        facultyDao = db.facultyDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    private suspend fun insertFacultyIfNotExists() {
        // Insert the corresponding faculty entry if it does not exist
        val faculty = FacultySchema(
            id=1,
            facultyId = "01",
            name = "Faculty of Engineering and Technology",
            departmentCount = 7
        )
        facultyDao.upsertFaculty(faculty)
    }

    @Test
    fun testUpsertAndRetrieveDepartment() = runBlocking {
        insertFacultyIfNotExists()

        val department = DepartmentSchema(
            id = 1,
            deptId = "01",
            shortname = "CSE",
            name = "Computer Science and Engineering",
            membersCount = 25,
            facultyId = "01"
        )

        departmentDao.upsertDepartment(department)

        val retrievedDepartment = departmentDao.getDepartmentById(department.facultyId, department.deptId)
        println("Inserted department: ${department.name}")
        println("Retrieved department: ${retrievedDepartment?.name}")

        assertEquals(department.name, retrievedDepartment?.name)
    }

    @Test
    fun testUpsertMultipleDepartments() = runBlocking {
        insertFacultyIfNotExists()

        departmentDao.upsertDepartments(sampleDepartments)

        val retrievedDepartments = departmentDao.getAllDepartments()
        println("Number of departments retrieved: ${retrievedDepartments.size}")

        assertEquals(sampleDepartments.size, retrievedDepartments.size)
    }

    @Test
    fun testGetDepartmentsByFaculty() = runBlocking {
        insertFacultyIfNotExists()

        departmentDao.upsertDepartments(sampleDepartments)

        val retrievedDepartments = departmentDao.getDepartmentsByFaculty("01")
        println("Departments retrieved for faculty: $retrievedDepartments")

        assertEquals(7, retrievedDepartments.size)
    }

    @Test
    fun testDeleteDepartment() = runBlocking {
        insertFacultyIfNotExists()

        val department = DepartmentSchema(
            id = 1,
            deptId = "01",
            shortname = "CSE",
            name = "Computer Science and Engineering",
            membersCount = 25,
            facultyId = "01"
        )

        departmentDao.upsertDepartment(department)
        println("Inserted department: ${department.name}")

        departmentDao.deleteDepartment(department)

        val retrievedDepartment = departmentDao.getDepartmentById(department.facultyId, department.deptId)
        println("Department after deletion: ${retrievedDepartment?.name}")

        assertNull(retrievedDepartment)
    }

    @Test
    fun testClearAllDepartments() = runBlocking {
        insertFacultyIfNotExists()

        departmentDao.upsertDepartments(sampleDepartments)

        departmentDao.clearAllDepartments()

        val retrievedDepartments = departmentDao.getAllDepartments()
        println("Number of departments after clearing: ${retrievedDepartments.size}")

        assertEquals(0, retrievedDepartments.size)
    }

    @Test
    fun testRetrieveEmptyDatabase() = runBlocking {
        val retrievedDepartments = departmentDao.getAllDepartments()
        println("Departments from empty database: $retrievedDepartments")

        assertEquals(0, retrievedDepartments.size)
    }
}
