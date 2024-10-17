@file:Suppress("unused")

package core.database.api.dbapi

import core.database.api.AcademicApiFacade
import domain.api.AcademicApi

/**
 * - Just to hide the `domain` module component to the consumer module
 * of this module, because `Domain.AcademicApi` is not accessible from the consumer module of this module
 */
class DbAcademicApiFacade(
    private val contractDbApi: AcademicApi,
) : AcademicApiFacade {

    //TODO:INSERT
    override suspend fun insertFaculty(json: String) = contractDbApi.insertFaculty(json)
    override suspend fun insertDept(facultyId: String, json: String) =
        contractDbApi.insertDept(facultyId, json)

    override suspend fun insertTeacher(deptId: String, json: String) =
        contractDbApi.insertTeacher(deptId, json)

    //TODO:READ
    override suspend fun readAllFaculty() = contractDbApi.readAllFaculty()
    override suspend fun readFacultyById(id: String) = contractDbApi.readFacultyById(id)
    override suspend fun readDeptById(id: String) = contractDbApi.readDeptById(id)
    override suspend fun readAllTeachers() = contractDbApi.readAllTeacher()
    override suspend fun readTeachersUnderDept(deptId: String) =
        contractDbApi.readTeachersUnderDept(deptId)

    override suspend fun readTeacherById(teacherId: String) =
        contractDbApi.readTeacherById(teacherId)

    override suspend fun readAllDept() = contractDbApi.readAllDept()
    override suspend fun readAllDeptUnderFaculty(facultyId: String) =
        contractDbApi.deptUnderFaculty(facultyId)

    //TODO:UPDATE
    override suspend fun updateFaculty(facultyId: String, json: String) =
        contractDbApi.updateFaculty(
            facultyId = facultyId,
            json = json
        )

    override suspend fun updateDept(deptId: String, json: String) = contractDbApi.updateDept(
        deptId = deptId,
        json = json
    )

    override suspend fun updateTeacher(teacherId: String, json: String) =
        contractDbApi.updateTeacher(
            teacherId = teacherId,
            json = json
        )

}