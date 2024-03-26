package realm.test

import core.database.realm.academic.AcademicLocalDataStore
import core.database.realm.academic.DepartmentEntityLocalModel
import core.database.realm.academic.DesignationLocalModel
import core.database.realm.academic.FacultyLocalModel
import core.database.realm.academic.TeacherLocalModel
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class AcademicLocalDataStoreTest {
    @Test
    fun `add FacultyEntity test`() {
        //before run it delete the old database or configuration file
        runBlocking {
            val requestModel = FacultyLocalModel(
                id = 1, facultyId = "1", name = "Test Faculty", deptCount = 1
            )
            val responseModel = AcademicLocalDataStore.addFaculty(requestModel)
           // println(responseModel)
            assertEquals(requestModel, responseModel.getOrNull())
        }

    }

    @Test
    fun `checking primary key test`() {
        runBlocking {
            val requestModel = FacultyLocalModel(
                id = 1, facultyId = "1", name = "Test Faculty", deptCount = 1
            )

            AcademicLocalDataStore.addFaculty(requestModel)
            val result = AcademicLocalDataStore.addFaculty(requestModel)
            ///
            println(result)
            assertTrue(result.isFailure)
        }

    }

    @Test
    fun `retrieve all FacultyEntity test`() {
        runBlocking {
            val requestModel = FacultyLocalModel(
                id = 1, facultyId = "1", name = "Test Faculty", deptCount = 1
            )
            AcademicLocalDataStore.addFaculty(requestModel)
            val responseModel = AcademicLocalDataStore.retrieveFaculties()
            responseModel.getOrNull()?.let { assertTrue(it.isNotEmpty()) }
            println(responseModel)
        }

    }

    @Test
    fun `add DepartmentInfoEntity test`() {
        // Before running this test, make sure to delete the old database or configuration file
        runBlocking {
            val requestModel = DepartmentEntityLocalModel(
                deptId = "01",
                employeeCount = 10,
                name = "Test Department",
                shortname = "TD"
            )
            val responseModel = AcademicLocalDataStore.addDepartment("1",requestModel)
            println(responseModel)
            assertEquals(requestModel, responseModel.getOrNull())
        }
    }
    @Test
    fun `add TeacherEntity test`() {
        // Before running this test, make sure to delete the old database or configuration file
        runBlocking {
            val requestModel = TeacherLocalModel(
                uid = "teacherea",
                name = "John Doe",
                username = "john.doe",
                email = "johndoe@example.com",
                achievement = "Best Teacher 2020",
                additionalEmail = "johndoealt@example.com",
                phone = "1234567890",
                profileImage = "http://example.com/image.jpg",
                roomNo = 101,
                type = 1,
                designations = listOf(DesignationLocalModel(name = "Professor")),
                deptId = "01",
                present = 1,
                departmentName = "Physics",
                shortName = "PHY"
            )

            val responseModel = AcademicLocalDataStore.addTeacher(requestModel)
            println(responseModel)
            assertEquals(requestModel, responseModel.getOrNull())
        }
    }
    @Test
    fun `retrieve all teacherList test`() {
        runBlocking {
            val responseModel = AcademicLocalDataStore.retrieveTeachers("01")
            println(responseModel)
        }

    }
}