@file:Suppress("SpellCheckingInspection")
package database

import core.database.db.Database
import core.database.getDatabase
import database.schema.DepartmentSubSchema
import database.schema.FacultyMemberSchema
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import kotlin.test.Test
import kotlin.test.assertNull

class FacultyMemberDaoTest {

    private lateinit var db: Database
    private lateinit var facultyMemberDao: core.database.dao.FacultyMemberDao

    @Before
    fun createDb() {
        db = getDatabase() // Assume this returns an instance of your Room database
        facultyMemberDao = db.facultyMemberDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun testUpsertAndRetrieveFacultyMember() = runBlocking {
        val department = DepartmentSubSchema(
            name = "Computer Science and Engineering",
            shortname = "CSE",
            designation = "Assistant Professor",
            roomNo = "268",
            present = 0
        )
        val facultyMember = FacultyMemberSchema(
            uid = "02d6adb8-d989-4ac9-a",
            deptId = "CSE",  // Set deptId for the test
            name = "Monishanker Halder",
            email = "m.halder@just.edu.bd",
            role = "Member",
            phone = "01727-653115",
            achievement = "BSc Engg. and MSc Engg. in CSE (JUST)",
            profile = null,
            additionalEmail = null,
            type = 1,
            departments = listOf(department)
        )

        facultyMemberDao.upsertFacultyMember(facultyMember)

        val retrievedFacultyMember = facultyMemberDao.getFacultyMemberById(facultyMember.uid)
        println("Inserted faculty member: $facultyMember")
        println("Retrieved faculty member: $retrievedFacultyMember")

        assertEquals(facultyMember.name, retrievedFacultyMember?.name)
    }

    @Test
    fun testClearFacultyMembers() = runBlocking {
        val department = DepartmentSubSchema(
            name = "Computer Science and Engineering",
            shortname = "CSE",
            designation = "Assistant Professor",
            roomNo = "268",
            present = 0
        )
        val facultyMember = FacultyMemberSchema(
            uid = "02d6adb8-d989-4ac9-a",
            deptId = "CSE",  // Set deptId for the test
            name = "Monishanker Halder",
            email = "m.halder@just.edu.bd",
            role = "Member",
            phone = "01727-653115",
            achievement = "BSc Engg. and MSc Engg. in CSE (JUST)",
            profile = null,
            additionalEmail = null,
            type = 1,
            departments = listOf(department)
        )

        facultyMemberDao.upsertFacultyMember(facultyMember)
        println("Inserted faculty member: ${facultyMember.name}")

        facultyMemberDao.deleteAllFacultyMembers()

        val retrievedFacultyMember = facultyMemberDao.getFacultyMemberById(facultyMember.uid)
        println("Faculty member after clearing: ${retrievedFacultyMember?.name}")

        assertNull(retrievedFacultyMember)
    }

    @Test
    fun testUpdateFacultyMember() = runBlocking {
        val department = DepartmentSubSchema(
            name = "Computer Science and Engineering",
            shortname = "CSE",
            designation = "Assistant Professor",
            roomNo = "268",
            present = 0
        )
        val facultyMember = FacultyMemberSchema(
            uid = "02d6adb8-d989-4ac9-a",
            deptId = "CSE",  // Set deptId for the test
            name = "Monishanker Halder",
            email = "m.halder@just.edu.bd",
            role = "Member",
            phone = "01727-653115",
            achievement = "BSc Engg. and MSc Engg. in CSE (JUST)",
            profile = null,
            additionalEmail = null,
            type = 1,
            departments = listOf(department)
        )

        facultyMemberDao.upsertFacultyMember(facultyMember)
        println("Inserted faculty member: ${facultyMember.name}")

        // Update the faculty member's name and upsert again
        val updatedFacultyMember = facultyMember.copy(name = "Updated Name")
        facultyMemberDao.upsertFacultyMember(updatedFacultyMember)
        println("Updated faculty member: ${updatedFacultyMember.name}")

        val retrievedFacultyMember = facultyMemberDao.getFacultyMemberById(facultyMember.uid)
        println("Retrieved updated faculty member: ${retrievedFacultyMember?.name}")

        assertEquals("Updated Name", retrievedFacultyMember?.name)
    }

    @Test
    fun testRetrieveEmptyDatabase() = runBlocking {
        val retrievedFacultyMembers = facultyMemberDao.getAllFacultyMembers()
        println("Faculty members from empty database: $retrievedFacultyMembers")

        assertEquals(0, retrievedFacultyMembers.size)
    }

    @Test
    fun testRetrieveFacultyMembersByDeptId() = runBlocking {
        val department = DepartmentSubSchema(
            name = "Computer Science and Engineering",
            shortname = "CSE",
            designation = "Assistant Professor",
            roomNo = "268",
            present = 0
        )
        val facultyMember = FacultyMemberSchema(
            uid = "02d6adb8-d989-4ac9-a",
            deptId = "CSE",  // Set deptId for the test
            name = "Monishanker Halder",
            email = "m.halder@just.edu.bd",
            role = "Member",
            phone = "01727-653115",
            achievement = "BSc Engg. and MSc Engg. in CSE (JUST)",
            profile = null,
            additionalEmail = null,
            type = 1,
            departments = listOf(department)
        )

        // Insert the faculty member
        facultyMemberDao.upsertFacultyMember(facultyMember)

        // Query by deptId
        val retrievedFacultyMembers = facultyMemberDao.getFacultyMembersByDeptId("CSE")
        println("Faculty members in CSE department: $retrievedFacultyMembers")

        assertEquals(1, retrievedFacultyMembers.size)
        assertEquals("Monishanker Halder", retrievedFacultyMembers[0].name)
    }
}
