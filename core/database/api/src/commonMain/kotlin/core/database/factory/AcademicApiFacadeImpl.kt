package core.database.factory

import core.database.api.AcademicApiFacade
import core.database.server.ServerAcademicApi

import core.roomdb.factory.getRoomDBFactory
import domain.factory.ContractFactory

class AcademicApiFacadeImpl internal constructor(
    token: String?
) : AcademicApiFacade {
    private val serverApi: ServerAcademicApi? = token?.let { ApiFactory.serverApi(it) }
    private val roomApi = getRoomDBFactory().createAcademicApi2()
    private val feedbackService = ContractFactory.feedbackService()
    private val readService = ContractFactory.academicReadEntityService()

    override suspend fun insertFaculty(json: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun insertDept(facultyId: String, json: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun insertTeacher(deptId: String, json: String): String {
        TODO("Not yet implemented")
    }

    /**Not handle any exception,propagate out the exception**/
    @Throws(Throwable::class)
    override suspend fun readAllFaculty(): String {
        if (serverApi == null) return roomApi.readFaculties()
        val serverResponse = serverApi.readFaculties()
        val isSuccess = readService.isFacultyListReadEntity(serverResponse)

        if (isSuccess)
            roomApi.insertFaculty(serverResponse)
        else {
            //Failed to load from remote , let fetch from local cache(room)
            val roomDbResponse = roomApi.readFaculties()
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
        if (serverApi == null) return roomApi.readTeachersUnderDept(deptId)
        val serverResponse = serverApi.readTeachersUnderDept(deptId)
        val isSuccess = readService.isTeacherListReadEntity(serverResponse)

        if (isSuccess)
            println(roomApi.insertTeacher(deptId = deptId, json = serverResponse))
        else {
            //Failed to load from remote , let fetch from local cache(room)
            val roomDbResponse = roomApi.readFaculties()
            if (!roomDbResponse.isFailed())
                return roomDbResponse
        }
        //If both failed then propagating the server feedback/failure message
        return serverResponse
    }

    override suspend fun readTeacherById(teacherId: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun readAllDept(): String {
        TODO("Not yet implemented")
    }

    override suspend fun readDeptsUnderFaculty(facultyId: String): String {
        if (serverApi == null) return roomApi.readDeptsUnderFaculty(facultyId)
        val serverResponse = serverApi.readDeptsUnderFaculty(facultyId)
        val isSuccess = readService.isDeptListReadEntity(serverResponse)

        if (isSuccess)
            roomApi.insertDept(facultyId = facultyId, json = serverResponse)
        else {
            //Failed to load from remote , let fetch from local cache(room)
            val roomDbResponse = roomApi.readFaculties()
            if (!roomDbResponse.isFailed())
                return roomDbResponse
        }
        //If both failed then propagating the server feedback/failure message
        return serverResponse
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