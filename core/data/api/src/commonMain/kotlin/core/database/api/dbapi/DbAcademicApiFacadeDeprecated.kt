@file:Suppress("unused")

package core.database.api.dbapi

import core.database.api.AcademicApiFacadeDeprecated
import domain.api.AcademicApiDeprecated

/*
 * - Just to hide the `domain` module component to the consumer module
 * of this module, because `Domain.AcademicApi` is not accessible from the consumer module of this module
 */
class DbAcademicApiFacadeDeprecated(
    private val contract: AcademicApiDeprecated,
) : AcademicApiFacadeDeprecated {

    //TODO:INSERT
    override suspend fun insertFaculty(json: String) = contract.upsetFacultyList(json)
    override suspend fun insertDept(facultyId: String, json: String) =
        contract.insertDept(facultyId, json)

    override suspend fun insertTeacher(deptId: String, json: String) =
        contract.insertTeacher(deptId, json)

    //TODO:READ
    override suspend fun readAllFaculty() = contract.readFaculties()
    override suspend fun readFacultyById(id: String) = contract.readFacultyById(id)
    override suspend fun readDeptById(id: String) = contract.readDeptById(id)
    override suspend fun readAllTeachers() = contract.readAllTeacher()
    override suspend fun readTeachersUnderDept(deptId: String) =
        contract.readTeachersUnderDept(deptId)

    override suspend fun readTeacherById(teacherId: String) =
        contract.readTeacherById(teacherId)

    override suspend fun readAllDept() = contract.readAllDept()
    override suspend fun readDeptsUnderFaculty(facultyId: String) =
        contract.readDeptsUnderFaculty(facultyId)

    //TODO:UPDATE
    override suspend fun updateFaculty(facultyId: String, json: String) =
        contract.updateFaculty(
            facultyId = facultyId,
            json = json
        )

    override suspend fun updateDept(deptId: String, json: String) = contract.updateDept(
        deptId = deptId,
        json = json
    )

    override suspend fun updateTeacher(teacherId: String, json: String) =
        contract.updateTeacher(
            teacherId = teacherId,
            json = json
        )

    override suspend fun deleteFaculty(id: String)=contract.deleteFaculty(id)

    override suspend fun deleteDepartment(id: String)=
        contract.deleteDepartment(id)
    override suspend fun deleteTeacher(id: String)=contract.deleteTeacher(id)
}