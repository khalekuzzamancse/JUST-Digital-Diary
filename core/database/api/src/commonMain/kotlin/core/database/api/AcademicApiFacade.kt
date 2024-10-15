@file:Suppress("unused")
package core.database.api

import domain.api.AcademicApi

/**
 * - Just to hide the `domain` module component to the consumer module
 * of this module, because `Domain.AcademicApi` is not accessible from the consumer module of this module
 * - Need not a abstract version of of it because it like a use case means it all dependencies are abstract and it has
 * no implementation, so it behaves same as abstract though it is concrete class
 */
class AcademicApiFacade(
    private val contractApi: AcademicApi,
){

    //TODO:INSERT
    suspend fun insertFaculty(json: String) =contractApi.insertFaculty(json)
    suspend fun insertDept(facultyId: String, json: String) = contractApi.insertDept(facultyId, json)
    suspend fun insertTeacher(deptId: String, json: String) =contractApi.insertTeacher(deptId, json)
    //TODO:READ
    suspend fun readAllFaculty() = contractApi.readAllFaculty()
    suspend fun readFacultyById(id: String) = contractApi.readFacultyById(id)
    suspend fun readDeptById(id: String) = contractApi.readDeptById(id)
    suspend fun readAllTeachers() = contractApi.readAllTeacher()
    suspend fun readTeachersUnderDept(deptId: String) =contractApi.readTeachersUnderDept(deptId)
    suspend fun readTeacherById(teacherId: String) =contractApi.readTeacherById(teacherId)
    suspend fun readAllDept() =contractApi.readAllDept()
    suspend fun readAllDeptUnderFaculty(facultyId: String) =contractApi.deptUnderFaculty(facultyId)
    //TODO:UPDATE
     suspend fun updateFaculty(facultyId: String, json: String)=contractApi.updateFaculty(
        facultyId = facultyId,
        json = json
    )
     suspend fun updateDept(deptId: String, json: String)=contractApi.updateDept(
         deptId = deptId,
         json = json
     )
     suspend fun updateTeacher(teacherId: String, json: String)= contractApi.updateTeacher(
         teacherId = teacherId,
         json = json
     )

}