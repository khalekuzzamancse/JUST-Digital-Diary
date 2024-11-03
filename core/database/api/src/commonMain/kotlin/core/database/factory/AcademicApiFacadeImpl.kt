package core.database.factory

import core.database.api.AcademicApiFacade
import domain.factory.ContractFactory

class AcademicApiFacadeImpl internal constructor(
  token: String?
) : AcademicApiFacade {
    private val roomApi = ApiFactory.roomAcademicApiFacade()
    private val   serverApi = token?.let { ApiFactory.serverApi(it) }
    private val feedbackService = ContractFactory.feedbackService()
    override suspend fun insertFaculty(json: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun insertDept(facultyId: String, json: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun insertTeacher(deptId: String, json: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun readAllFaculty(): String {
        if (serverApi == null) return roomApi.readAllFaculty()

        val serverResponse = serverApi.readAllFaculty()
        if (serverResponse.isFailed()) {
            //Failed to load from remote , let fetch from local database
            val roomDbResponse = roomApi.readAllFaculty()
            if (!roomDbResponse.isFailed())
                return roomDbResponse
        }
        //If both failed then propagating the server feedback/failure message
        return serverResponse


    }

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

    private fun String.isFailed() = feedbackService.isFeedbackEntity(json = this)
}