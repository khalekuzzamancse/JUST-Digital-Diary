package academic.presentationlogic.mapper

import faculty.domain.model.public_.DepartmentModel
import faculty.domain.model.public_.FacultyModel
import faculty.domain.model.public_.TeacherModel

private typealias UiFacultyModel = academic.presentationlogic.model.public_.FacultyModel
private typealias UiDepartmentModel = academic.presentationlogic.model.public_.DepartmentModel
private typealias TeacherUiModel = academic.presentationlogic.model.public_.TeacherModel

internal object PublicModelMapper {
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




}