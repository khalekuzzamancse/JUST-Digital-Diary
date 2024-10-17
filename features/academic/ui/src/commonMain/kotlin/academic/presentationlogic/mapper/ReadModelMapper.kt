@file:Suppress("unused")
package academic.presentationlogic.mapper

import academic.presentationlogic.model.TeacherReadModel
import academic.presentationlogic.model.TeacherWriteModel
import faculty.domain.model.DepartmentReadModel
import faculty.domain.model.FacultyReadModel

/**
 * Need to send down the priorities to the UI,or domain, filter the list data layer based on priorities
 */
internal object ReadModelMapper {
    fun FacultyReadModel.toModel() = academic.presentationlogic.model.FacultyReadModel(
        id = facultyId,
        name = name,
        numberOfDepartment = departmentsCount.toString()
    )

    fun DepartmentReadModel.toModel() = academic.presentationlogic.model.DepartmentReadModel(
        name = name,
        shortname = shortname,
        id = deptId,
        membersCount = numberOfEmployee.toString(),
        facultyId = facultyId

    )

    fun faculty.domain.model.TeacherReadModel.toModel() = TeacherReadModel(
        name = name,
        id = id,
        deptId = deptId,
        email = email,
        additionalEmail = additionalEmail,
        phone = phone,
        achievements = achievements,
        roomNo = roomNo,
        profileImageLink = imageLink,
        designations = designations
    )

    fun faculty.domain.model.TeacherReadModel.toEntryModel() = TeacherWriteModel(
        name = name,
        deptId = deptId,
        email = email,
        additionalEmail = additionalEmail,
        phone = phone,
        achievements = achievements,
        roomNo = roomNo,
        profileImageLink = imageLink,
        designations = designations,
        priority = priority.toString()
    )

}