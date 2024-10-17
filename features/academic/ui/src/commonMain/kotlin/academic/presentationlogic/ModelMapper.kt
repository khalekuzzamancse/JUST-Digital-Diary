@file:Suppress("unused")
package academic.presentationlogic

import academic.presentationlogic.model.DepartmentWriteModel
import academic.presentationlogic.model.FacultyWriteModel
import academic.presentationlogic.model.TeacherReadModel
import academic.presentationlogic.model.TeacherWriteModel
import faculty.domain.model.DepartmentReadModel
import faculty.domain.model.FacultyReadModel

/**
 * Need to send down the priorities to the UI,or domain, filter the list data layer based on priorities
 */
internal object ModelMapper {
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

    @Throws(NumberFormatException::class)
    fun FacultyWriteModel.toDomainModelOrThrow() =
        faculty.domain.model.FacultyWriteModel(
            priority = this.priority.toInt(),
            name = this.name,
        )

    @Throws(NumberFormatException::class)
    fun DepartmentWriteModel.toDomainModelOrThrow() =
        faculty.domain.model.DepartmentWriteModel(
            priority = this.priority.toInt(),
            name = this.name,
            shortname = this.shortname,
            facultyId = this.facultyId
        )

    @Throws(NumberFormatException::class)
    fun TeacherWriteModel.toDomainModelOrThrow() = faculty.domain.model.TeacherWriteModel(
        deptId = deptId,
        priority = this.priority.toInt(),
        name = this.name,
        email = this.email,
        additionalEmail = this.additionalEmail,
        achievements = this.achievements,
        phone = phone,
        designations = designations,
        roomNo = roomNo,
        profileImageLink = profileImageLink?.ifBlank { null },
    )


}