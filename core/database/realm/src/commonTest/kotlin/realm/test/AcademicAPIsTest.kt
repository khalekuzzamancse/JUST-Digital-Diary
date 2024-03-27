package realm.test

import database.local.api.AcademicAPIs
import database.local.schema.academic.DepartmentEntityLocal
import database.local.schema.academic.DesignationEntityLocal
import database.local.schema.academic.FacultyEntityLocal
import database.local.schema.academic.TeacherEntityLocal
import kotlinx.coroutines.runBlocking
import java.util.UUID
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class AcademicAPIsTest {
    @Test
    fun `add FacultyEntity test`() {
        //before run it delete the old database or configuration file
        runBlocking {
            val requestModel = FacultyEntityLocal(
                id = 1, facultyId = "1", name = "Test Faculty", deptCount = 1
            )
            val responseModel = AcademicAPIs.addFaculty(requestModel)
           println(responseModel)
            assertEquals(requestModel, responseModel.getOrNull())
        }

    }

    @Test
    fun `checking primary key test`() {
        runBlocking {
            val requestModel = FacultyEntityLocal(
                id = 1, facultyId = "1", name = "Test Faculty", deptCount = 1
            )
            val result = AcademicAPIs.addFaculty(requestModel)
            ///
            println(result)
            assertTrue(result.isFailure)
        }

    }

    @Test
    fun `retrieve all FacultyEntity test`() {
        runBlocking {
            val requestModel = FacultyEntityLocal(
                id = 1, facultyId = "1", name = "Test Faculty", deptCount = 1
            )
            AcademicAPIs.addFaculty(requestModel)
            val responseModel = AcademicAPIs.retrieveFaculties()
            responseModel.getOrNull()?.let { assertTrue(it.isNotEmpty()) }
            println(responseModel)
        }

    }

    @Test
    fun `add DepartmentInfoEntity test`() {
        // Before running this test, make sure to delete the old database or configuration file
        runBlocking {
            val requestModel = DepartmentEntityLocal(
                deptId = "01",
                employeeCount = 10,
                name = "Test Department",
                shortname = "TD",
                id = 1
            )
            val responseModel = AcademicAPIs.addDepartment("1",requestModel)
            println(responseModel)
            assertEquals(requestModel, responseModel.getOrNull())
        }
    }
    @Test
    fun `retrieve department `() {
        // Before running this test, make sure to delete the old database or configuration file
        runBlocking {
            val requestModel = DepartmentEntityLocal(
                deptId = "01",
                employeeCount = 10,
                name = "Test Department",
                shortname = "TD",
                id = 1
            )
            AcademicAPIs.addDepartment("1",requestModel)
            val responseModel = AcademicAPIs.retrieveDepartments("1")
            println(responseModel)
            responseModel.getOrNull()?.let { assertTrue(it.isNotEmpty()) }
        }
    }
    @Test
    fun `add TeacherEntity test`() {
        // Before running this test, make sure to delete the old database or configuration file
        runBlocking {
            val requestModel = TeacherEntityLocal(
                uid = UUID.randomUUID().toString(),
                name = "John Doe",
                email = "johndoe@example.com",
                achievement = "Best Teacher 2020",
                additionalEmail = "johndoealt@example.com",
                phone = "1234567890",
                profileImage = "http://example.com/image.jpg",
                roomNo = 101,
                designations = listOf(DesignationEntityLocal(name = "Professor")),
                deptId = "01",
                departmentName = "Physics",
                shortName = "PHY",
                id = 1
            )
            val responseModel = AcademicAPIs.addTeacher(requestModel)
            println(responseModel)
            assertEquals(requestModel, responseModel.getOrNull())
        }
    }
    @Test
    fun `retrieve all teacherList test`() {
        runBlocking {
            val responseModel = AcademicAPIs.retrieveTeachers("01")
            println(responseModel)
        }

    }
}