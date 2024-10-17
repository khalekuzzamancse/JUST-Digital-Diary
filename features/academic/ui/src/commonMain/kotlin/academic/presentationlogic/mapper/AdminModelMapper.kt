package academic.presentationlogic.mapper

import academic.presentationlogic.model.DepartmentWriteModel
import academic.presentationlogic.model.FacultyWriteModel
import academic.presentationlogic.model.TeacherWriteModel

internal object AdminModelMapper {
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