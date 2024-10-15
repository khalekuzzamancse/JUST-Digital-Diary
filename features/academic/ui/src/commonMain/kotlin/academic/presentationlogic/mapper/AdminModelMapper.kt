package academic.presentationlogic.mapper

import academic.presentationlogic.model.admin.DepartmentEntryModel
import academic.presentationlogic.model.admin.FacultyEntryModel
import academic.presentationlogic.model.admin.TeacherEntryModel

internal object AdminModelMapper {
    @Throws(NumberFormatException::class)
    fun FacultyEntryModel.toDomainModelOrThrow() =
        faculty.domain.model.admin.FacultyEntryModel(
            priority = this.priority.toInt(),
            name = this.name,
        )

    @Throws(NumberFormatException::class)
    fun DepartmentEntryModel.toDomainModelOrThrow() =
        faculty.domain.model.admin.DepartmentEntryModel(
            priority = this.priority.toInt(),
            name = this.name,
            shortname = this.shortname,
            facultyId = this.facultyId
        )

    @Throws(NumberFormatException::class)
    fun TeacherEntryModel.toDomainModelOrThrow() = faculty.domain.model.admin.TeacherEntryModel(
        deptId = deptId,
        priority = this.priority.toInt(),
        name = this.name,
        email = this.email,
        additionalEmail = this.additionalEmail,
        achievements = this.achievements,
        phone = phone,
        designations = designations,
        roomNo = roomNo,
        profileImageLink = profileImageLink?.ifBlank{null},
    )

    fun faculty.domain.model.admin.DepartmentEntryModel.toUiModel() =
        DepartmentEntryModel(
            facultyId = facultyId,
            name = name,
            priority = priority.toString(),
            shortname = shortname
        )

    fun faculty.domain.model.admin.TeacherEntryModel.toUIModel() = TeacherEntryModel(
        deptId = this.deptId,
        priority = this.priority.toString(),
        name = this.name,
        email = this.email,
        additionalEmail = this.additionalEmail,
        achievements = this.achievements,
        phone = this.phone,
        designations = this.designations,
        roomNo = this.roomNo,
        profileImageLink = profileImageLink
    )


}