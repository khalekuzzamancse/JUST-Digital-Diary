package core.database.api.room

import core.database.api.AcademicApiFacade
import domain.api.AcademicApi

class RoomAcademicApiFacade(private val contract: AcademicApi): AcademicApiFacade {
    override suspend fun insertFaculty(json: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun insertDept(facultyId: String, json: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun insertTeacher(deptId: String, json: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun readAllFaculty()=contract.readAllFaculty()


    override suspend fun readFacultyById(id: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun readDeptById(id: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun readAllTeachers(): String {
        TODO("Not yet implemented")
    }

    override suspend fun readTeachersUnderDept(deptId: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun readTeacherById(teacherId: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun readAllDept(): String {
        TODO("Not yet implemented")
    }

    override suspend fun readAllDeptUnderFaculty(facultyId: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun updateFaculty(facultyId: String, json: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun updateDept(deptId: String, json: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun updateTeacher(teacherId: String, json: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFaculty(id: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun deleteDepartment(id: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTeacher(id: String): String {
        TODO("Not yet implemented")
    }
}