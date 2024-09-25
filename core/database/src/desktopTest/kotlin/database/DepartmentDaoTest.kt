@file:Suppress("SpellCheckingInspection")

package database

import database.dao.DepartmentDao
import database.schema.DepartmentSchema
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import kotlin.test.Test
import kotlin.test.assertNull

class DepartmentDaoTest {

    private lateinit var db: Database
    private lateinit var departmentDao: DepartmentDao

    private val sampleDepartments = listOf(
        DepartmentSchema(1, "01", "CSE", "Computer Science and Engineering", 25, "Faculty of Engineering and Technology"),
        DepartmentSchema(2, "11", "EEE", "Electrical and Electronic Engineering", 17, "Faculty of Engineering and Technology"),
        DepartmentSchema(51, "07", "IPE", "Industrial and Production Engineering", 18, "Faculty of Engineering and Technology"),
        DepartmentSchema(52, "22", "TE", "Textile Engineering", 9, "Faculty of Engineering and Technology"),
        DepartmentSchema(53, "05", "CHE", "Chemical Engineering", 20, "Faculty of Engineering and Technology"),
        DepartmentSchema(54, "08", "PME", "Petroleum and Mining Engineering", 13, "Faculty of Engineering and Technology"),
        DepartmentSchema(55, "19", "BME", "Biomedical Engineering", 9, "Faculty of Engineering and Technology")
    )

    @Before
    fun createDb() {
        db = getDatabase() // Assuming this sets up the in-memory database
        departmentDao = db.departmentDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun testUpsertAndRetrieveDepartment() = runBlocking {
        val department = DepartmentSchema(
            id = 1,
            dept_id = "01",
            shortname = "CSE",
            name = "Computer Science and Engineering",
            membersCount = 25,
            faculty_name = "Faculty of Engineering and Technology"
        )

        departmentDao.upsertDepartment(department)

        val retrievedDepartment = departmentDao.getDepartmentById(department.id)
        println("Inserted department: ${department.name}")
        println("Retrieved department: ${retrievedDepartment?.name}")

        assertEquals(department.name, retrievedDepartment?.name)
    }

    @Test
    fun testUpsertMultipleDepartments() = runBlocking {
        departmentDao.upsertDepartments(sampleDepartments)

        val retrievedDepartments = departmentDao.getAllDepartments()
        println("Number of departments retrieved: ${retrievedDepartments.size}")

        assertEquals(sampleDepartments.size, retrievedDepartments.size)
    }

    @Test
    fun testGetDepartmentsByFaculty() = runBlocking {
        departmentDao.upsertDepartments(sampleDepartments)

        val retrievedDepartments = departmentDao.getDepartmentsByFaculty("Faculty of Engineering and Technology")
        println("Departments retrieved for faculty: $retrievedDepartments")

        assertEquals(7, retrievedDepartments.size)
    }

    @Test
    fun testDeleteDepartment() = runBlocking {
        val department = DepartmentSchema(
            id = 1,
            dept_id = "01",
            shortname = "CSE",
            name = "Computer Science and Engineering",
            membersCount = 25,
            faculty_name = "Faculty of Engineering and Technology"
        )

        departmentDao.upsertDepartment(department)
        println("Inserted department: ${department.name}")

        departmentDao.deleteDepartment(department)

        val retrievedDepartment = departmentDao.getDepartmentById(department.id)
        println("Department after deletion: ${retrievedDepartment?.name}")

        assertNull(retrievedDepartment)
    }

    @Test
    fun testClearAllDepartments() = runBlocking {
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
