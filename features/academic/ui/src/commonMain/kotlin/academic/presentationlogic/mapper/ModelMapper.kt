package academic.presentationlogic.mapper

import academic.presentationlogic.model.admin.DepartmentEntryModel
import academic.presentationlogic.model.admin.FacultyEntryModel
import academic.presentationlogic.model.admin.TeacherEntryModel
import academic.presentationlogic.model.public_.Dept
import faculty.domain.model.public_.DepartmentModel
import faculty.domain.model.public_.FacultyModel
import faculty.domain.model.public_.TeacherModel
import kotlin.jvm.Throws

private typealias UiFacultyModel = academic.presentationlogic.model.public_.FacultyModel
private typealias UiDepartmentModel = academic.presentationlogic.model.public_.DepartmentModel
private typealias TeacherUiModel = academic.presentationlogic.model.public_.TeacherModel

internal object ModelMapper {
    fun toUiFacultyModel(model: FacultyModel) = UiFacultyModel(
        name = model.name,
        id = model.facultyId,
        numberOfDepartment = "${model.departmentsCount}"
    )

    fun FacultyModel.toUiModel() = UiFacultyModel(
        name = this.name,
        id = this.facultyId,
        numberOfDepartment = this.departmentsCount.toString()
    )

    fun toUiFacultyModel(model: DepartmentModel) =
        UiDepartmentModel(
            name = model.name,
            id = model.deptId,
            shortname = model.shortname,
            membersCount = model.employeeCount.toString()
        )

    fun toTeacherUiModel(model: TeacherModel) = TeacherUiModel(
        id=model.id,
        name = model.name,
        email = model.email,
        additionalEmail = model.additionalEmail ?: "",
        phone = model.phone,
        achievements = model.achievements,
        profileImageLink = model.profile,
        roomNo = model.roomNo?:"",
        designations = model.designations,
    )


    fun FacultyEntryModel.toDomainModelOrThrow() =
        faculty.domain.model.admin.FacultyEntryModel(
            priority = this.priority.toInt(),
            name = this.name,
        )

    fun DepartmentEntryModel.toDomainModelOrThrow() =
        faculty.domain.model.admin.DepartmentEntryModel(
            priority = this.priority.toInt(),
            name = this.name,
            shortname = this.shortName,
            facultyId = this.facultyId
        )

    @Throws
    fun TeacherEntryModel.toDomainModelOrThrow() = faculty.domain.model.admin.TeacherEntryModel(
        deptId = deptId,
        priority = this.priority.toInt(),
        name = this.name,
        email = this.email,
        additionalEmail = this.additionalEmail,
        achievements = this.achievements,
        phone = phone,
        designations = designations,
        roomNo = roomNo

    )


}