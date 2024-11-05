@file:Suppress("SpellCheckingInspection")

package database

import core.roomdb.dao.FacultyDao
import core.roomdb.db.DB
import core.roomdb.schema.DepartmentSchema
import core.roomdb.schema.FacultySchema
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import core.roomdb.factory.getDatabase
import kotlin.test.Test
import kotlin.test.assertNull

class DepartmentSubSchemaDaoTest {

    private lateinit var db: DB
    private lateinit var departmentDao: core.roomdb.dao.DepartmentDao
    private lateinit var facultyDao: FacultyDao

    // Provided sample departments data
    private val sampleDepartments = listOf(
        DepartmentSchema(
            id = 1,
            deptId = "01",
            shortname = "CSE",
            name = "Computer Science and Engineering",
            facultyId = "01"
        ),
        DepartmentSchema(
            id = 2,
            deptId = "11",
            shortname = "EEE",
            name = "Electrical and Electronic Engineering",
            facultyId = "01"
        ),
        DepartmentSchema(
            id = 51,
            deptId = "07",
            shortname = "IPE",
            name = "Industrial and Production Engineering",
            facultyId = "01"
        ),
        DepartmentSchema(
            id = 52,
            deptId = "22",
            shortname = "TE",
            name = "Textile Engineering",
            facultyId = "01"
        ),
        DepartmentSchema(
            id = 53,
            deptId = "05",
            shortname = "CHE",
            name = "Chemical Engineering",
            facultyId = "01"
        ),
        DepartmentSchema(
            id = 54,
            deptId = "08",
            shortname = "PME",
            name = "Petroleum and Mining Engineering",
            facultyId = "01"
        ),
        DepartmentSchema(
            id = 55,
            deptId = "19",
            shortname = "BME",
            name = "Biomedical Engineering",
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
            priority=1,
            facultyId = "01",
            name = "Faculty of Engineering and Technology",
        )
        facultyDao.upsertFaculty(faculty)
    }

    @Test
    fun upset() = runBlocking {
        insertFacultyIfNotExists()
        val department = DepartmentSchema(
            id = 1,
            deptId = "01",
            shortname = "CSE",
            name = "Computer Science and Engineering",
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
    fun readAllDept() = runBlocking {
        val retrievedDepartments = departmentDao.getAllDepartments()
        println("Departments retrieved for faculty: $retrievedDepartments")

    }
    @Test
    fun readDepartmentByFacultyId() = runBlocking {
        val retrievedDepartments = departmentDao.getDepartmentsByFaculty("01")
        println("Departments retrieved for faculty: $retrievedDepartments")

    }

    @Test
    fun testDeleteDepartment() = runBlocking {
        insertFacultyIfNotExists()

        val department = DepartmentSchema(
            id = 1,
            deptId = "01",
            shortname = "CSE",
            name = "Computer Science and Engineering",
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
